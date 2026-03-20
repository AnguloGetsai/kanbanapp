package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.model.StudentTask;

import java.util.List;

public interface StudentTaskRepositoryPort {
    void save(StudentTask studentTask);
    void saveAll(List<StudentTask> studentTaskList);
}
