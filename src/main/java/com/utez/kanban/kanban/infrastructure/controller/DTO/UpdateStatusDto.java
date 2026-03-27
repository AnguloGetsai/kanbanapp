package com.utez.kanban.kanban.infrastructure.controller.DTO;

public class UpdateStatusDto {
    private String status; // ToDo, Doing, Done

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
