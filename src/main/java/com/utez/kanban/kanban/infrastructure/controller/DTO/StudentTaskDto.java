package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.StudentTask;

public class StudentTaskDto {
    private Long taskID;
    private String name;
    private String description;
    private String statusKanban;
    private String color;

    public StudentTaskDto(Long taskID, String name, String description, String statusKanban, String color) {
        this.taskID = taskID;
        this.name = name;
        this.description = description;
        this.statusKanban = statusKanban;
        this.color = color;
    }

    public static StudentTaskDto fromDomain(StudentTask st){
        return new StudentTaskDto(
                st.getTask().getTaskID(),
                st.getTask().getName(),
                st.getTask().getDescription(),
                st.getStatus(),
                st.getTask().getColor()
        );
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
}
