package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Student;




public interface StudentRepositoryPort {
    Student saveBasicInformation(Student student);
}
