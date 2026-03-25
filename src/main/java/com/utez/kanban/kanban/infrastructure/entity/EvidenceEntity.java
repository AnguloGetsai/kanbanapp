package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "evidence")
public class EvidenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evidenceID;

    @Column(name = "comment")
    private String comment;

    @Column(name = "studentID")
    private Long studentID;

    @Column(name = "taskID")
    private Long taskID;

    public EvidenceEntity() {}

    // ✅ SETTERS
    public void setComment(String comment) { this.comment = comment; }

    public void setStudentID(Long studentID) { this.studentID = studentID; }

    public void setTaskID(Long taskID) { this.taskID = taskID; }

    public Long getEvidenceID() { return evidenceID; }
}