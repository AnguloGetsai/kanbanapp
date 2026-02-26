package com.utez.kanban.kanban.infrastructure.controller.userDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserCredentialDTO {
    @NotBlank(message =  "Email is required")
    @Email(message = "The email address is invalid")
    @Size(max = 100, message = "Maximum 100 characters")
    private String email;

    @NotBlank(message = "The verification code is invalid")
    @Pattern(regexp = "\\d{6}", message = "The code must contain exactly 6 digits")
    private String code;

    @NotBlank(message = "Password is required")
    @Size(min = 10, max = 30, message = "The password must contain between 10 and 30 characters")
    private String password;

    public UserCredentialDTO(String email, String code) {
        this.email = email;
        this.code = code;
    }


    public UserCredentialDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
