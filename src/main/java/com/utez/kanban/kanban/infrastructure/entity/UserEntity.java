package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String password;
    private String email;
    private String rol;
    private boolean status;
    private String verificationToken;
    private boolean isVerified;

    @Column(name = "reset_token")
    private String resetToken;

    public UserEntity() {
    }

    public UserEntity(String password, String email, String rol,
                      boolean status, String verificationToken, boolean isVerified,
                      String resetToken) {
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.status = status;
        this.verificationToken = verificationToken;
        this.isVerified = isVerified;
        this.resetToken = resetToken;
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

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}
