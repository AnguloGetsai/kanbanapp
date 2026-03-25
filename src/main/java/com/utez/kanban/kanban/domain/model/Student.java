package com.utez.kanban.kanban.domain.model;

import java.util.List;

public class Student {
    private Long studentID;
    private String firstName;
    private String lastName;
    private String gender;
    private String image;


    private User user;
    private List<Notification> notifications;


    //nuevo campo
    private boolean statusAdviser;


    public Student(Long studentID, String firstName, String lastName,
                   String gender, String image, User user,
                   List<Notification> notifications) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.image = image;
        this.user = user;
        this.notifications = notifications;
    }

    public Student(){}

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public boolean isStatusAdviser() {
        return statusAdviser;
    }

    public void setStatusAdviser(boolean statusAdviser) {
        this.statusAdviser = statusAdviser;
    }
}
