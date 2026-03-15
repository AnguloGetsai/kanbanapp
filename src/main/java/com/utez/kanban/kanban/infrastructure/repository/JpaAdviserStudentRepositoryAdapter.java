package com.utez.kanban.kanban.infrastructure.repository;


import com.utez.kanban.kanban.domain.model.AdviserStudent;

import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.port.out.AdviserStudentRepository;

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

    public JpaAdviserStudentRepositoryAdapter(JpaAdviserStudentRepository jpaAdviserStudentRepository){
        this.jpaAdviserStudentRepository = jpaAdviserStudentRepository;
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
}
