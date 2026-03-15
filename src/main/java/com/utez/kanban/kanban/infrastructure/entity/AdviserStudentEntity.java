package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "adviser_student")
public class AdviserStudentEntity {
    @EmbeddedId
    AdviserStudentID id;

    @ManyToOne
    @MapsId("adviserID")
    @JoinColumn(name = "adviserID")
    private AdviserEntity adviserEntity;

    @ManyToOne
    @MapsId("studentID")
    @JoinColumn(name = "studentID")
    private StudentEntity studentEntity;


    private boolean status;


    public AdviserStudentEntity(AdviserStudentID id, AdviserEntity adviserEntity,
                                StudentEntity studentEntity, boolean status) {
        this.id = id;
        this.adviserEntity = adviserEntity;
        this.studentEntity = studentEntity;
        this.status = status;
    }

    public AdviserStudentEntity() {
    }


    public AdviserStudentID getId() {
        return id;
    }

    public void setId(AdviserStudentID id) {
        this.id = id;
    }

    public AdviserEntity getAdviserEntity() {
        return adviserEntity;
    }

    public void setAdviserEntity(AdviserEntity adviserEntity) {
        this.adviserEntity = adviserEntity;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
