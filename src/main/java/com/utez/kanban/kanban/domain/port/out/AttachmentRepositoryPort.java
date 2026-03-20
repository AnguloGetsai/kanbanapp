package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Attachment;
import com.utez.kanban.kanban.domain.model.Task;

import java.util.List;

public interface AttachmentRepositoryPort {
    void saveAttachment(Attachment attachment);
    void saveAll(List<Attachment> attachmentList);
}
