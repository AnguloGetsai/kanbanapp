package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.Student;

import java.util.Base64;

public class StudentProfileDto {
    private Long studentID;
    private String firstName;
    private String lastName;
    private String email;
    private String logo;

    public StudentProfileDto(Long studentID, String firstName, String lastName, String email, String logo) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.logo = logo;
    }

    public static StudentProfileDto fromDomain(Student student){
        String logoB64 = "";

        if(student.getImage() != null){
            logoB64 = Base64.getEncoder().encodeToString(student.getImage());
        }

        return new StudentProfileDto(
                student.getStudentID(),
                student.getFirstName(),
                student.getLastName(),
                student.getUser().getEmail(),
                logoB64
        );
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
