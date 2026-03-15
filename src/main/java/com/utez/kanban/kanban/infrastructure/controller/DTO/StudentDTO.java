package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.Student;

public class StudentDTO {
    private Long studentID;
    private String firstName;
    private String lastName;
    private String email;
    private boolean statusAdviserStudent;
    // pendiente de agregar el logo

    public StudentDTO(Long studentID, String firstName, String lastName, String email, boolean statusAdviserStudent) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.statusAdviserStudent = statusAdviserStudent;


    }

    public static StudentDTO toStudentDTO(Student student){
        return new StudentDTO(
                student.getStudentID(),
                student.getFirstName(),
                student.getLastName(),
                student.getUser().getEmail(),
                student.isStatusAdviser()
        );
    }

    public StudentDTO(){}


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

    public boolean isStatusAdviserStudent() {
        return statusAdviserStudent;
    }

    public void setStatusAdviserStudent(boolean statusAdviserStudent) {
        this.statusAdviserStudent = statusAdviserStudent;
    }
}
