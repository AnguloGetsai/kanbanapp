package com.utez.kanban.kanban.infrastructure.controller.DTO;

import java.util.List;

public class AdviserReportDto {
    private int totalStudents;
    private int totalTasks;
    private int tasksToDo;
    private int tasksDoing;
    private int tasksDone;
    private Double averageGrade;

    private List<TaskDetailReportDto> taskDetailReportDto;

    public AdviserReportDto(int totalStudents, int totalTasks, int tasksToDo, int tasksDoing, int tasksDone, Double averageGrade, List<TaskDetailReportDto> taskDetailReportDto) {
        this.totalStudents = totalStudents;
        this.totalTasks = totalTasks;
        this.tasksToDo = tasksToDo;
        this.tasksDoing = tasksDoing;
        this.tasksDone = tasksDone;
        this.averageGrade = averageGrade;
        this.taskDetailReportDto = taskDetailReportDto;
    }


    public int getTotalStudents() { return totalStudents; }
    public void setTotalStudents(int totalStudents) { this.totalStudents = totalStudents; }
    public int getTotalTasks() { return totalTasks; }
    public void setTotalTasks(int totalTasks) { this.totalTasks = totalTasks; }
    public int getTasksToDo() { return tasksToDo; }
    public void setTasksToDo(int tasksToDo) { this.tasksToDo = tasksToDo; }
    public int getTasksDoing() { return tasksDoing; }
    public void setTasksDoing(int tasksDoing) { this.tasksDoing = tasksDoing; }
    public int getTasksDone() { return tasksDone; }
    public void setTasksDone(int tasksDone) { this.tasksDone = tasksDone; }
    public Double getAverageGrade() { return averageGrade; }
    public void setAverageGrade(Double averageGrade) { this.averageGrade = averageGrade; }

    public List<TaskDetailReportDto> getTaskDetailReportDto() {
        return taskDetailReportDto;
    }

    public void setTaskDetailReportDto(List<TaskDetailReportDto> taskDetailReportDto) {
        this.taskDetailReportDto = taskDetailReportDto;
    }
}
