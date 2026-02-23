package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String password;
    private String email;
    private String rol;
    private boolean status;

    @Column(name = "verificationCode")
    private String verificationCode;

    private boolean isVerified;

    @Column(name = "expirationTime")
    private LocalDateTime expirationTime;

    public UserEntity(Long userID, String password, String email, String rol, boolean status,
                      String verificationCode, boolean isVerified, LocalDateTime expirationTime) {
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.status = status;
        this.verificationCode = verificationCode;
        this.isVerified = isVerified;
        this.expirationTime = expirationTime;
    }

    public UserEntity( String password, String email, String rol, boolean status,
                      String verificationCode, boolean isVerified, LocalDateTime expirationTime) {

        this.password = password;
        this.email = email;
        this.rol = rol;
        this.status = status;
        this.verificationCode = verificationCode;
        this.isVerified = isVerified;
        this.expirationTime = expirationTime;
    }

    public UserEntity() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
