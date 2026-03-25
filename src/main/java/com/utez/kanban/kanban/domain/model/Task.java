package com.utez.kanban.kanban.domain.model;

import java.time.LocalDate;

public class Task {

    private Long taskID; // alias legacy: id
    private String title;
    private String description;

    // En tu esquema: statusKanban (ToDo, Doing, Done)
    private String statusKanban;

    // En tu esquema: creationDate / limitDate
    private LocalDate creationDate;
    private LocalDate limitDate;

    private String color;
    private String priority;

    private Board board;

    // Relación simplificada para algunos flujos
    private Student student;

    // Evidencia (legacy: actualmente se guarda directo en la tabla TASK/TaskEntity)
    private byte[] evidence;

    public Task() {}

    // Constructor esperado por TaskDTO
    public Task(LocalDate limitDate,
                LocalDate startDate,
                String name,
                String description,
                String statusKanban,
                String color,
                String priority) {
        this.limitDate = limitDate;
        this.creationDate = startDate;
        this.title = name;
        this.description = description;
        this.statusKanban = statusKanban;
        this.color = color;
        this.priority = priority;
    }

    // --- Getters/Setters compatibles con el resto del código ---
    public Long getTaskID() { return taskID; }
    public void setTaskID(Long taskID) { this.taskID = taskID; }

    // alias legacy para mappers existentes
    public Long getId() { return taskID; }
    public void setId(Long id) { this.taskID = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // nombre legado
    public String getStatus() { return statusKanban; }
    public void setStatus(String status) { this.statusKanban = status; }

    public String getStatusKanban() { return statusKanban; }
    public void setStatusKanban(String statusKanban) { this.statusKanban = statusKanban; }

    public LocalDate getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }

    public LocalDate getLimitDate() { return limitDate; }
    public void setLimitDate(LocalDate limitDate) { this.limitDate = limitDate; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public Board getBoard() { return board; }
    public void setBoard(Board board) { this.board = board; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public byte[] getEvidence() { return evidence; }
    public void setEvidence(byte[] evidence) { this.evidence = evidence; }
}