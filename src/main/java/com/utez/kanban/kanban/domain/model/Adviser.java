package com.utez.kanban.kanban.domain.model;

public class Adviser {
    private Long adviserID;
    private String firstName;
    private String lastName;
    private String image;

    private User user;
    private Admin admin;






    public Adviser(Long adviserID, String firstName, String lastName, String image, User user, Admin admin) {
        this.adviserID = adviserID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.user = user;
        this.admin = admin;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
