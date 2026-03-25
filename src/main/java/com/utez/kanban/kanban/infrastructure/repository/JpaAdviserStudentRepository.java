package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.AdviserStudentEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JpaAdviserStudentRepository extends JpaRepository<AdviserStudentEntity, Long> {
    @Query("""
SELECT ase
FROM AdviserStudentEntity ase
WHERE ase.adviserId = :id
""")
    List<AdviserStudentEntity> findAdviserStudentEntitis(@Param("id") Long id);

    @Query("""
            SELECT ase
            FROM AdviserStudentEntity ase
            WHERE ase.studentId = :studentId AND ase.status = true
            """)
    List<AdviserStudentEntity> findActiveAdviserStudentsByStudentId(@Param("studentId") Long studentId);


    @Transactional
    @Modifying
    @Query("""
            UPDATE AdviserStudentEntity ase
            SET ase.status = :status
            WHERE ase.adviserId = :adviserID AND ase.studentId = :studentID
            """)
    int changeStatus(@Param("status") boolean status, @Param("adviserID") Long adviserID, @Param("studentID") Long studentID);
}
