package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;
import org.apache.el.lang.ELArithmetic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaStudentRepository extends JpaRepository<StudentEntity, Long> {
    @Query("""
SELECT se
FROM StudentEntity  se
JOIN se.userEntity ue
WHERE ue.email = :email AND ue.rol = 'STUDENT'
""")
    Optional<StudentEntity> getStudentEntitiesByEmail(@Param("email") String email);

    @Query("""
            SELECT se
            FROM AdviserStudentEntity ase
            JOIN ase.studentEntity se
            WHERE ase.adviserId = :id
            """)
    List<StudentEntity> getStudentByAdviserID(@Param("id") Long id);
}
