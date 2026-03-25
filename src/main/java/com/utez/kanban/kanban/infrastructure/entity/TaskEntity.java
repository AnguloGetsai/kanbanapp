package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskID;


    //private LocalDate culminationDate; atributo eliminado
    private LocalDate limitDate;
    private LocalDate creationDate;
    private String name;
    private String description;
    private String statusKanban;

    // nuevos atributos
    private String color;
    private String priority;





    @ManyToOne
    @JoinColumn(name = "boardID")
    private BoardEntity boardEntity;

    public TaskEntity(Long taskID, LocalDate limitDate, LocalDate creationDate,
                      String name, String description, String statusKanban,
                      String color, String priority, BoardEntity boardEntity) {
        this.taskID = taskID;
        this.limitDate = limitDate;
        this.creationDate = creationDate;
        this.name = name;
        this.description = description;
        this.statusKanban = statusKanban;
        this.color = color;
        this.priority = priority;
        this.boardEntity = boardEntity;
    }

    public TaskEntity(){

    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }


    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusKanban() {
        return statusKanban;
    }

    public void setStatusKanban(String statusKanban) {
        this.statusKanban = statusKanban;
    }

    public BoardEntity getBoardEntity() {
        return boardEntity;
    }

    public void setBoardEntity(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
