package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Task;
import com.utez.kanban.kanban.infrastructure.entity.TaskEntity;

public class TaskMapper {
    public static Task toTask(TaskEntity taskEntity){
        return new Task(
                taskEntity.getTaskID(),
                taskEntity.getLimitDate(),
                taskEntity.getCreationDate(),
                taskEntity.getName(),
                taskEntity.getDescription(),
                taskEntity.getStatusKanban(),
                taskEntity.getColor(),
                taskEntity.getPriority(),
                BoardMapper.toBoard(taskEntity.getBoardEntity())
        );
    }


    public static TaskEntity toTaskEntity(Task task){
        return new TaskEntity(
                task.getTaskID(),
                task.getLimitDate(),
                task.getCreationDate(),
                task.getName(),
                task.getDescription(),
                task.getStatusKanban(),
                task.getColor(),
                task.getPriority(),
                BoardMapper.toBoardEntity(task.getBoard())
        );
    }
}
