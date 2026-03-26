package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.model.StudentTask;

import java.util.List;
import java.util.Optional;

public interface StudentTaskRepositoryPort {
    void save(StudentTask studentTask);
    void saveAll(List<StudentTask> studentTaskList);
    List<StudentTask> getTasksByStudentAndAdviser(String email, Long adviserID);
    Optional<StudentTask> getTaskDetail(String email, Long taskID);
}
