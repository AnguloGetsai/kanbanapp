package com.utez.kanban.kanban.infrastructure.controller.DTO;

import java.util.List;

public class StudentExpedienteDto {
    // Perfil
    private String fullName;
    private String email;
    private Integer age;
    private boolean isActive;

    // Métricas
    private int totalTasks;
    private int tasksToDo;
    private int tasksDoing;
    private int tasksDone;
    private Double averageGrade;
    private Double onTimePercentage; // Porcentaje de entregas a tiempo

    // Historial
    private List<TaskDetailReportDto> taskHistory;

    public StudentExpedienteDto(String fullName, String email, Integer age, boolean isActive, int totalTasks, int tasksToDo, int tasksDoing, int tasksDone, Double averageGrade, Double onTimePercentage, List<TaskDetailReportDto> taskHistory) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.isActive = isActive;
        this.totalTasks = totalTasks;
        this.tasksToDo = tasksToDo;
        this.tasksDoing = tasksDoing;
        this.tasksDone = tasksDone;
        this.averageGrade = averageGrade;
        this.onTimePercentage = onTimePercentage;
        this.taskHistory = taskHistory;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public int getTasksToDo() {
        return tasksToDo;
    }

    public void setTasksToDo(int tasksToDo) {
        this.tasksToDo = tasksToDo;
    }

    public int getTasksDoing() {
        return tasksDoing;
    }

    public void setTasksDoing(int tasksDoing) {
        this.tasksDoing = tasksDoing;
    }

    public int getTasksDone() {
        return tasksDone;
    }

    public void setTasksDone(int tasksDone) {
        this.tasksDone = tasksDone;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public Double getOnTimePercentage() {
        return onTimePercentage;
    }

    public void setOnTimePercentage(Double onTimePercentage) {
        this.onTimePercentage = onTimePercentage;
    }

    public List<TaskDetailReportDto> getTaskHistory() {
        return taskHistory;
    }

    public void setTaskHistory(List<TaskDetailReportDto> taskHistory) {
        this.taskHistory = taskHistory;
    }
}
