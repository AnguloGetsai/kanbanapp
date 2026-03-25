package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "adviser_student")
public class AdviserStudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long adviserId;
    private Long studentId;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public Long getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(Long adviserId) {
        this.adviserId = adviserId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }
}