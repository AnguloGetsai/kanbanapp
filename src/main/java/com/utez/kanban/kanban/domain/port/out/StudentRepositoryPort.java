package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Student;

import java.util.Optional;


public interface StudentRepositoryPort {
    Student saveStudent(Student student);
    Optional<Student> findById(Long id);
    Optional<Student> findByEmail(String email);
}
