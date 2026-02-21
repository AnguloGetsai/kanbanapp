package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Student;
import org.springframework.stereotype.Repository;



public interface StudentRepositoryPort {
    Student saveBasicInformation(Student student);
}
