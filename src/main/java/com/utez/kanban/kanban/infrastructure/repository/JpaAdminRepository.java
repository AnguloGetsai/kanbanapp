package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAdminRepository extends JpaRepository<AdminEntity, Long> {
}
