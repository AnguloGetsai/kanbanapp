package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.port.out.StudentRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;
import com.utez.kanban.kanban.infrastructure.mapper.StudentMapper;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class JpaStudentRepositoryAdapter implements StudentRepositoryPort {

    private final JpaStudentRepository jpaStudentRepository;

    public JpaStudentRepositoryAdapter(JpaStudentRepository jpaStudentRepository){
        this.jpaStudentRepository = jpaStudentRepository;
    }


    @Override
    public Student saveStudent(Student student) {
        return StudentMapper.toStudent(jpaStudentRepository.save(StudentMapper.toStudentEntity(student)));
    }

    @Override
    public Optional<Student> findById(Long id) {
        return jpaStudentRepository.findById(id)
                .map(StudentMapper::toStudent);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return jpaStudentRepository.getStudentEntitiesByEmail(email)
                .map(StudentMapper::toStudent);
    }

    @Override
    public List<Student> getStudentByAdviserID(Long id) {
        List<Student> studentList = new ArrayList<>();

        for(StudentEntity studentEntity: jpaStudentRepository.getStudentByAdviserID(id)){
            studentList.add(StudentMapper.toStudent(studentEntity));
        }
        return studentList;
    }



}
