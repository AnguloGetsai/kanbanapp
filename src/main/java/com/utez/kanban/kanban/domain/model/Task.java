package com.utez.kanban.kanban.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Task {
    private Long taskID;
    private LocalDate limitDate;
    private LocalDate creationDate;
    private String name;
    private String description;
    private String statusKanban;

    private String color;
    private String priority;

    private List<Attachment> attachments;

    private Board board;

    public Task(Long taskID, LocalDate limitDate,
                LocalDate creationDate, String name,
                String description, String statusKanban,
                String color, String priority, Board board) {
        this.taskID = taskID;
        this.limitDate = limitDate;
        this.creationDate = creationDate;
        this.name = name;
        this.description = description;
        this.statusKanban = statusKanban;
        this.color = color;
        this.priority = priority;
        this.board = board;
    }

    public Task(LocalDate limitDate, LocalDate creationDate, String name, String description, String statusKanban, String color, String priority) {
        this.limitDate = limitDate;
        this.creationDate = creationDate;
        this.name = name;
        this.description = description;
        this.statusKanban = statusKanban;
        this.color = color;
        this.priority = priority;
    }

    public Task(){

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

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
