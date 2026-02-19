package com.utez.kanban.kanban.domain.student.model;

import com.utez.kanban.kanban.domain.user.model.User;

public class Student extends User {
    private Long studentID;
    private String firstName;
    private String lastName;
    private String image;


    public Student() {
    }

    public Student(Long studentID, String firstName, String lastName, String image) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
