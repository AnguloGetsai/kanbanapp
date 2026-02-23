package com.utez.kanban.kanban.domain.model;

import java.time.LocalDateTime;

public class Email {
    private String email;
    private boolean verified;
    private LocalDateTime expirationTime;
    private String code;


    public Email(){

    }

    public Email(String email, boolean verified, LocalDateTime expirationTime, String code) {
        this.email = email;
        this.verified = verified;
        this.expirationTime = expirationTime;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
