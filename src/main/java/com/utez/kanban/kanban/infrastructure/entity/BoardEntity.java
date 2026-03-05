package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardID;
    private String subject;

    @OneToOne
    @JoinColumn(name = "adviserID")
    private AdviserEntity adviserEntity;

    @OneToMany(mappedBy = "boardEntity")
    private List<TaskEntity> tasks;


    public BoardEntity() {
    }

    public BoardEntity(Long boardID, String subject, AdviserEntity adviserEntity) {
        this.boardID = boardID;
        this.subject = subject;
        this.adviserEntity = adviserEntity;
    }


    public Long getBoardID() {
        return boardID;
    }

    public void setBoardID(Long boardID) {
        this.boardID = boardID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public AdviserEntity getAdviserEntity() {
        return adviserEntity;
    }

    public void setAdviserEntity(AdviserEntity adviserEntity) {
        this.adviserEntity = adviserEntity;
    }
}
