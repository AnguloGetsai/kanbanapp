package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.StudentTask;

import java.util.List;

public interface StudentTaskUseCase {
    List<StudentTask> findByTaskId(Long taskId);
}
