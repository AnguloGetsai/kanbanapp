package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.infrastructure.entity.AdviserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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


    @Transactional
    @Modifying
    @Query("""
            UPDATE AdviserEntity ae
            SET ae.image = :logo
            WHERE ae.adviserID = :id
            """)
    int uploadLogo(@Param("id") Long id, @Param("logo") byte[] logo);


    @Transactional
    @Modifying
    @Query("""
               UPDATE AdviserEntity ae
               SET ae.firstName = :firstName, ae.lastName = :lastName
               WHERE ae.adviserID = :id
                """)
    int updateAdviserInformation(@Param("id") Long id,@Param("firstName") String firstName,@Param("lastName") String lastName);


    @Query("""
    SELECT ase.adviserEntity
    FROM AdviserStudentEntity ase
    JOIN ase.studentEntity se
    JOIN se.userEntity ue
    WHERE ue.email = :email
    """)
    List<AdviserEntity> getAdvisersByStudentEmail(@Param("email") String email);



    @Query("SELECT ase.status FROM AdviserStudentEntity ase WHERE ase.studentEntity.studentID = :studentID AND ase.adviserEntity.adviserID = :adviserID")
    Optional<Boolean> findStatusByStudentAndAdviser(@Param("studentID") Long studentID, @Param("adviserID") Long adviserID);

    }
