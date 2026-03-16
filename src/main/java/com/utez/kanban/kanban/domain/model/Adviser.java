package com.utez.kanban.kanban.domain.model;

public class Adviser {
    private Long adviserID;
    private String firstName;
    private String lastName;
    private byte[] image;

    private User user;
    private Admin admin;






    public Adviser(Long adviserID, String firstName, String lastName, byte[] image, User user, Admin admin) {
        this.adviserID = adviserID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.user = user;
        this.admin = admin;
    }

    public Adviser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Adviser() {
    }

    public Long getAdviserID() {
        return adviserID;
    }

    public void setAdviserID(Long adviserID) {
        this.adviserID = adviserID;
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
