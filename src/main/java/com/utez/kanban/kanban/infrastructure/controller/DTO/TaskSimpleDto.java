package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.Task;

public class TaskSimpleDto {

    private Long id;
    private String name;
    private String statusKanban;
    private String color;

    public static TaskSimpleDto fromDomain(Task task){
        return new TaskSimpleDto(
                task.getTaskID(),
                task.getName(),
                task.getStatusKanban(),
                task.getColor()
        );
    }

    public TaskSimpleDto(Long id, String name, String statusKanban, String color){
        this.id = id;
        this.name = name;
        this.statusKanban = statusKanban;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
