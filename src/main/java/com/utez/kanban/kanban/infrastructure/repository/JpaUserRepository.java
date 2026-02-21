package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
