package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Task;
import com.utez.kanban.kanban.infrastructure.entity.TaskEntity;

public class TaskMapper {

    public static Task toTask(TaskEntity entity) {
        Task task = new Task();

        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        task.setDescription(entity.getDescription());
        task.setStatus(entity.getStatus());
        task.setEvidence(entity.getEvidence());

        return task;
    }

    public static TaskEntity toEntity(Task task) {
        TaskEntity entity = new TaskEntity();

        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setStatus(task.getStatus());
        entity.setEvidence(task.getEvidence());

        return entity;
    }

    // Alias por compatibilidad con mappers existentes
    public static TaskEntity toTaskEntity(Task task) {
        return toEntity(task);
    }
}