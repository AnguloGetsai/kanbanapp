package com.utez.kanban.kanban.domain.model;

public class Admin {
    private Long adminID;
    private String firstName;
    private String lastName;
    private byte[] image;

    private User user;

    public Admin(Long adminID, String firstName, String lastName, byte[] image, User user) {
        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.user = user;
    }


    public Admin(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public Admin() {

    }

    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
