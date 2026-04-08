package com.utez.kanban.kanban.infrastructure.controller.DTO;

import jakarta.validation.constraints.NotBlank;

public class ChangeStatusDTO {
    @NotBlank(message = "Status is required")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
