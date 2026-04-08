package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.EvidenceEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaEvidenceRepository extends JpaRepository<EvidenceEntity, Long> {
    List<EvidenceEntity> findByStudentTaskEntity(StudentTaskEntity studentTaskEntity);
}
