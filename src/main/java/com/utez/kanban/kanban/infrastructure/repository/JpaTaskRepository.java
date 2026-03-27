package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaTaskRepository extends JpaRepository<TaskEntity, Long> {


    @Query("""
SELECT t
FROM TaskEntity t
WHERE t.boardEntity.adviserEntity.adviserID = :adviserID
""")
    List<TaskEntity> findTasksByAdviserID(@Param("adviserID") Long adviserID);
}
