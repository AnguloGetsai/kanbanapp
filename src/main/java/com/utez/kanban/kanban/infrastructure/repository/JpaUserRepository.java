package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.UserEntity;

import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);


    @Transactional
    @Modifying
    @Query("""
            UPDATE UserEntity ue 
            SET ue.status = :status
            WHERE ue.email = :email
            """)
    int changeStatus(@Param("email") String email, @Param("status") boolean status);

    @Modifying
    @Transactional
    @Query("""
                UPDATE UserEntity ue
                SET ue.verificationCode = :verificationCode, ue.expirationTime = :expirationDate
                WHERE ue.email = :email
                """)
    int saveCode(@Param("email") String email, @Param("verificationCode") String verificationCode, @Param("expirationDate")LocalDateTime expirationDate);

    @Modifying
    @Transactional
    @Query("""
            UPDATE UserEntity ue
            SET ue.isVerified = :status, ue.passwordToken = :passwordToken, ue.passwordTokenExpiration = :passwordTokenExpiration
            WHERE ue.email = :email
            """)
    long authorizeVerification(@Param("email") String email, @Param("status") boolean status, @Param("passwordToken") String passwordToken, @Param("passwordTokenExpiration") LocalDateTime passwordTokenExpiration);

    @Modifying
    @Transactional
    @Query("""
              UPDATE UserEntity  ue
              SET ue.password = :password
              WHERE ue.email = :email
              """)
    long addPassword(@Param("email") String email, @Param("password") String password);
}
