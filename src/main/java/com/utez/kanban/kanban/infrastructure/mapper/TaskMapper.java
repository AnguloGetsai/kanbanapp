package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Attachment;
import com.utez.kanban.kanban.domain.model.Task;
import com.utez.kanban.kanban.infrastructure.entity.AttachmentEntity;
import com.utez.kanban.kanban.infrastructure.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;

public class TaskMapper {
    public static Task toTask(TaskEntity taskEntity){


        List<Attachment> attachments = new ArrayList<>();

        if(taskEntity.getAttachments() != null){
            for(AttachmentEntity a : taskEntity.getAttachments()){
                attachments.add(AttachmentMapper.toAttachment(a));
            }
        }

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
