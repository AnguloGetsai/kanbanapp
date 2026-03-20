package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Attachment;
import com.utez.kanban.kanban.domain.port.out.AttachmentRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.StudentTaskEntity;
import com.utez.kanban.kanban.infrastructure.mapper.AttachmentMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaAttachmentRepositoryAdapter implements AttachmentRepositoryPort {

    private final JpaAttachmentRepository jpaAttachmentRepository;

    public JpaAttachmentRepositoryAdapter(JpaAttachmentRepository jpaAttachmentRepository){
        this.jpaAttachmentRepository = jpaAttachmentRepository;
    }

    @Override
    public void saveAttachment(Attachment attachment) {
        jpaAttachmentRepository.save(AttachmentMapper.toAttachmentEntity(attachment));
    }


}
