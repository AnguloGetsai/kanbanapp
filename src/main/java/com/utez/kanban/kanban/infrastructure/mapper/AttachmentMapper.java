package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Attachment;
import com.utez.kanban.kanban.infrastructure.entity.AttachmentEntity;
import org.hibernate.query.NativeQuery;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


public class AttachmentMapper {
    public static Attachment toAttachment(AttachmentEntity attachmentEntity){
        return new Attachment(
                attachmentEntity.getAttachmentID(),
                attachmentEntity.getFileName(),
                attachmentEntity.getFileType(),
                attachmentEntity.getFileData(),
                TaskMapper.toTask(attachmentEntity.getTaskEntity())
        );
    }

    public static AttachmentEntity toAttachmentEntity(Attachment attachment){
        return new AttachmentEntity(
                attachment.getAttachmentID(),
                attachment.getFileName(),
                attachment.getFileType(),
                attachment.getFileData(),
                TaskMapper.toTaskEntity(attachment.getTask())
        );
    }
}
