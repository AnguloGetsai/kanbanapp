package com.utez.kanban.kanban.domain.model;

public class Student {
    private Long studentID;
    private String firstName;
    private String lastName;
    private String gender;
    private String image;

    public Student() {
    }

    public Student(Long studentID, String firstName, String lastName,String gender, String image) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.image = image;
    }

    public Student(String firstName, String lastName, String gender, String image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
