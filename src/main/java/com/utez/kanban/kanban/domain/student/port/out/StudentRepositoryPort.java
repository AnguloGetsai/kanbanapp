package com.utez.kanban.kanban.domain.student.port.out;

import com.utez.kanban.kanban.domain.student.model.Student;

public interface StudentRepositoryPort {
    boolean establishInformation(Student student);
}
