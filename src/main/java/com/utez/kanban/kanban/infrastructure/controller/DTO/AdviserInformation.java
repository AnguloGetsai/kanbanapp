package com.utez.kanban.kanban.infrastructure.controller.DTO;

public class AdviserInformation {
    String email;
    String firstName;
    String lastName;
    boolean status;

    public AdviserInformation(){

    }


    public AdviserInformation(String email, String firstName, String lastName, boolean status) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
