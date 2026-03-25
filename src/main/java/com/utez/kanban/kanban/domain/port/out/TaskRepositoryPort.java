package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryPort {

    Optional<Task> findById(Long id);

    Task save(Task task);

    List<Task> getTasksByStudent(Long studentId);
}