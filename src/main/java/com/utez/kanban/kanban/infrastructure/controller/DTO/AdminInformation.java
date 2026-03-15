package com.utez.kanban.kanban.infrastructure.controller.DTO;

public class AdminInformation {
    private String firstName;
    private String lastName;
    private String email;

    private String logo;



    public AdminInformation(){

    }

    public AdminInformation(String firstName, String lastName, String email, String logo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
