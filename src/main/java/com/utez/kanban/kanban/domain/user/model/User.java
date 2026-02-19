package com.utez.kanban.kanban.domain.user.model;

public abstract class User {

    //The purpose of this class is to be a superclass
    //to share theirs attributes and methods

    private Long userID;
    private String password;
    private String email;
    private String rol;
    private boolean status;

    public User() {
    }

    public User(Long userID, String password, String email, String rol, boolean status) {
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.status = status;
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
}
