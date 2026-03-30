package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Attachment;
import com.utez.kanban.kanban.infrastructure.entity.AttachmentEntity;



public class AttachmentMapper {
    public static Attachment toAttachment(AttachmentEntity attachmentEntity){
        return new Attachment(
                attachmentEntity.getAttachmentID(),
                attachmentEntity.getFileName(),
                attachmentEntity.getFileType(),
                attachmentEntity.getFileData(),
                null
        );
    }

    public static AttachmentEntity toAttachmentEntity(Attachment attachment){

        AttachmentEntity entity = new AttachmentEntity(
                attachment.getAttachmentID(),
                attachment.getFileName(),
                attachment.getFileType(),
                attachment.getFileData(),
                null
        );


        if(attachment.getTask() != null){
            entity.setTaskEntity(
                    TaskMapper.toTaskEntity(attachment.getTask())
            );
        }

        return entity;
    }
}
