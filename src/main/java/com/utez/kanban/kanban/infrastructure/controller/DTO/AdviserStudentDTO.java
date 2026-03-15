package com.utez.kanban.kanban.infrastructure.controller.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AdviserStudentDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "The email address is invalid")
    private String email;

    public AdviserStudentDTO(String email) {
        this.email = email;
    }
    public AdviserStudentDTO(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
