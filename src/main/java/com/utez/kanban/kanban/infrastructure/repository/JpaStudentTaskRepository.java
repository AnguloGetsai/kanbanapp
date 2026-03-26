package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface JpaStudentTaskRepository extends JpaRepository<StudentTaskEntity, Long> {
    @Query("""
SELECT st
FROM StudentTaskEntity st
JOIN st.studentEntity se
JOIN se.userEntity ue
JOIN st.taskEntity t
JOIN t.boardEntity b
JOIN b.adviserEntity a
WHERE ue.email = :email
AND a.adviserID = :adviserID
""")
    List<StudentTaskEntity> findTasksByStudentAndAdviser(
            @Param("email") String email,
            @Param("adviserID") Long adviserID
    );


    @Query("""
SELECT st
FROM StudentTaskEntity st
JOIN FETCH st.taskEntity t
LEFT JOIN FETCH t.attachments a
JOIN st.studentEntity se
JOIN se.userEntity ue
WHERE t.taskID = :taskID
AND ue.email = :email
""")
    Optional<StudentTaskEntity> findTaskDetailById(
            @Param("taskID") Long taskID,
            @Param("email") String email
    );
}
