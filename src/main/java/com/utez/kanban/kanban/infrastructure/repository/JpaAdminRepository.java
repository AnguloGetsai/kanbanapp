package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface JpaAdminRepository extends JpaRepository<AdminEntity, Long> {

    @Query(value = """
           SELECT ae
           FROM AdminEntity  ae
           JOIN ae.userEntity ue
           WHERE ue.email = :email
           """)
    Optional<AdminEntity> findAdminByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("""
            UPDATE AdminEntity
            SET image = :image
            WHERE adminID = :id
            """)
    int updateLogo(@Param("id") Long id, @Param("image") byte[] image);
}
