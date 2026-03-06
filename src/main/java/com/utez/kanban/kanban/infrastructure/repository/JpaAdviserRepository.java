package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.AdviserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAdviserRepository extends JpaRepository<AdviserEntity, Long> {
}
