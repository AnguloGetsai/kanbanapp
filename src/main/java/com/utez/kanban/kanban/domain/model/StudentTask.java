package com.utez.kanban.kanban.domain.model;

import java.time.LocalDate;

public class StudentTask {
    private Student student;
    private  Task task;

    private String status;
    private LocalDate assignedDate;
    private LocalDate culminationDate;

    public StudentTask(Student student, Task task,
                       String status, LocalDate assignedDate,
                       LocalDate culminationDate) {
        this.student = student;
        this.task = task;
        this.status = status;
        this.assignedDate = assignedDate;
        this.culminationDate = culminationDate;
    }

    public StudentTask(){}

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public LocalDate getCulminationDate() {
        return culminationDate;
    }

    public void setCulminationDate(LocalDate culminationDate) {
        this.culminationDate = culminationDate;
    }
}
