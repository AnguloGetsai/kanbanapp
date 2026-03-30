package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface JpaAttachmentRepository extends JpaRepository<AttachmentEntity, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM AttachmentEntity a WHERE a.taskEntity.taskID = :taskID")
    void deleteByTaskId(Long taskID);
}
