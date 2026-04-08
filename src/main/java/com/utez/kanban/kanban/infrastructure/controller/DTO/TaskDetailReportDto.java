package com.utez.kanban.kanban.infrastructure.controller.DTO;

import java.time.LocalDate;

public class TaskDetailReportDto {
    private String taskName;
    private String studentName;
    private LocalDate date;
    private String status;
    private Double grade;

    public TaskDetailReportDto(String taskName, String studentName, LocalDate date, String status, Double grade) {
        this.taskName = taskName;
        this.studentName = studentName;
        this.date = date;
        this.status = status;
        this.grade = grade;
    }

    // Getters y Setters
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getGrade() { return grade; }
    public void setGrade(Double grade) { this.grade = grade; }
}
