package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepositoryPort {

    Optional<Student> findById(Long id);

    Optional<Student> findByEmail(String email);

    Student saveStudent(Student student);

    List<Student> getStudentByAdviserID(Long id);
}