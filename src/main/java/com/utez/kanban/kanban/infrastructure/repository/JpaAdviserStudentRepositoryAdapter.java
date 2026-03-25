package com.utez.kanban.kanban.infrastructure.repository;


import com.utez.kanban.kanban.domain.model.AdviserStudent;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.port.out.AdviserStudentRepository;
import com.utez.kanban.kanban.domain.port.out.AdviserRepositoryPort;

import com.utez.kanban.kanban.infrastructure.entity.AdviserStudentEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;
import com.utez.kanban.kanban.infrastructure.mapper.AdviserStudentMapper;

import com.utez.kanban.kanban.infrastructure.mapper.StudentMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JpaAdviserStudentRepositoryAdapter implements AdviserStudentRepository {
    private final JpaAdviserStudentRepository jpaAdviserStudentRepository;
    private final AdviserRepositoryPort adviserRepositoryPort;

    public JpaAdviserStudentRepositoryAdapter(
            JpaAdviserStudentRepository jpaAdviserStudentRepository,
            AdviserRepositoryPort adviserRepositoryPort
    ){
        this.jpaAdviserStudentRepository = jpaAdviserStudentRepository;
        this.adviserRepositoryPort = adviserRepositoryPort;
    }


    @Override
    public void addStudentToBoard(AdviserStudent adviserStudent) {
        jpaAdviserStudentRepository.save(AdviserStudentMapper.toAdviserStudentEntity(adviserStudent));

    }

    @Override
    public List<Student> getAllStudents(Long id) {
        List<Student> studentList = new ArrayList<>();
        List<AdviserStudentEntity> adviserStudentEntities = jpaAdviserStudentRepository.findAdviserStudentEntitis(id);
        if(adviserStudentEntities != null){
            for(AdviserStudentEntity ase: adviserStudentEntities){
                Student student = StudentMapper.toStudent(ase.getStudentEntity());
                student.setStatusAdviser(ase.isStatus());
                studentList.add(student);
            }
        }
        return studentList;
    }

    @Override
    public boolean changeStatus(boolean status, Long adviserID, Long studentID) {
        return jpaAdviserStudentRepository.changeStatus(status, adviserID, studentID) > 0;
    }

    @Override
    public List<Adviser> getAdvisersByStudent(Long studentId) {
        if (studentId == null) return List.of();

        List<AdviserStudentEntity> entities = jpaAdviserStudentRepository.findActiveAdviserStudentsByStudentId(studentId);
        if (entities == null || entities.isEmpty()) return List.of();

        return entities.stream()
                .map(AdviserStudentMapper::toModel)
                .map(rel -> adviserRepositoryPort.findById(rel.getAdviserId()).orElse(null))
                .filter(adviser -> adviser != null && adviser.getUser() != null && adviser.getUser().isStatus())
                .toList();
    }

    @Override
    public boolean isAdviserActiveForStudent(Long adviserId, Long studentId) {
        if (adviserId == null || studentId == null) return false;

        boolean adviserActive = adviserRepositoryPort.findById(adviserId)
                .map(adviser -> adviser.getUser() != null && adviser.getUser().isStatus())
                .orElse(false);
        if (!adviserActive) return false;

        return jpaAdviserStudentRepository.findActiveAdviserStudentsByStudentId(studentId)
                .stream()
                .anyMatch(rel -> rel.getAdviserId().equals(adviserId));
    }
}
