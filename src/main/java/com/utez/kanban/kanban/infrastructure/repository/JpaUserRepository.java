package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    @Query("""
            SELECT COUNT (ue)
            FROM UserEntity ue
            WHERE ue.email = :email
            """)
    long findByEmailAndRol(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("""
                UPDATE UserEntity ue
                SET ue.verificationCode = :verificationCode, ue.expirationTime = :expirationDate
                WHERE ue.email = :email
                """)
    int saveCode(@Param("email") String email,
                  @Param("verificationCode") String verificationCode,
                  @Param("expirationDate")LocalDateTime expirationDate);
}
