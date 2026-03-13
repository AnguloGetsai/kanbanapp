package com.utez.kanban.kanban.infrastructure.controller.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserPasswordDTO {

    @NotBlank(message = "Password is required")
    @Size(min = 10, max = 30, message = "The password must contain between 10 and 30 characters")
    private String password;


    @NotBlank(message =  "Email is required")
    @Email(message = "The email address is invalid")
    @Size(max = 100, message = "Maximum 100 characters")
    private String email;

@NotBlank(message = "PasswordToken is required")
    private String passwordToken;

    public UserPasswordDTO(String password, String email, String passwordToken) {
        this.password = password;
        this.email = email;
        this.passwordToken = passwordToken;
    }

    public UserPasswordDTO() {
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }
}
