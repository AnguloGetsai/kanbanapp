package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.port.out.StudentRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;
import com.utez.kanban.kanban.infrastructure.mapper.StudentMapper;
import org.springframework.stereotype.Component;


@Component
public class JpaStudentRepositoryAdapter implements StudentRepositoryPort {

    private final JpaStudentRepository jpaStudentRepository;

    public JpaStudentRepositoryAdapter(JpaStudentRepository jpaStudentRepository){
        this.jpaStudentRepository = jpaStudentRepository;
    }
    @Override
    public Student saveBasicInformation(Student student) {
        StudentEntity  studentEntity = StudentMapper.toStudentEntity(student);
        studentEntity = jpaStudentRepository.save(studentEntity);
        return StudentMapper.toStudent(studentEntity);
    }



}
