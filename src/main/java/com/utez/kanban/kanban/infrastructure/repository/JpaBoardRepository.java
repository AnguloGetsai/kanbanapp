package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaBoardRepository extends JpaRepository<BoardEntity, Long> {
    @Query("""
            SELECT be
            FROM BoardEntity be
            JOIN be.adviserEntity ad
           WHERE ad.adviserID = :id
           """)
    Optional<BoardEntity> findBoardByAdviserID(@Param("id") Long id);
}
