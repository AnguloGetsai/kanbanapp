package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.StudentTaskEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentTaskID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaStudentTaskRepository extends JpaRepository<StudentTaskEntity, StudentTaskID> {

    Optional<StudentTaskEntity> findById_StudentIDAndId_TaskID(Long studentID, Long taskID);

    List<StudentTaskEntity> findById_StudentID(Long studentID);

    List<StudentTaskEntity> findById_StudentIDAndTaskEntity_BoardEntity_BoardID(
            Long studentID,
            Long boardID
    );
}