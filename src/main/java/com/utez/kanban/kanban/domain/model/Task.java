package com.utez.kanban.kanban.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Task {
    private Long taskID;
    private String name;
    private String description;
    private LocalDate creationDate;
    private LocalDate dueDate;
    private String status;

    private Column column;
    private List<Student> students;

    public Task(Long taskID, String name, String description, LocalDate creationDate,
                LocalDate dueDate, String status, Column column, List<Student> students) {
        this.taskID = taskID;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.status = status;
        this.column = column;
        this.students = students;
    }

    public Task() {
    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}