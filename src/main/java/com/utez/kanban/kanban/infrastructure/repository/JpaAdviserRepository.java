package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.AdviserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface JpaAdviserRepository extends JpaRepository<AdviserEntity, Long> {
    @Query("""
SELECT ae
FROM AdviserEntity  ae
JOIN ae.userEntity ue
WHERE ue.email = :email AND ue.rol = 'ADVISER'
""")
    Optional<AdviserEntity> getAdviserEntityByEmail(@Param("email") String email);
}
