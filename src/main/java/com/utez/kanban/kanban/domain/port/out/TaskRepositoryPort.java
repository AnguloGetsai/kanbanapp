package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Task;

import java.util.Optional;

public interface TaskRepositoryPort {
    Optional<Task> save(Task task);
}
