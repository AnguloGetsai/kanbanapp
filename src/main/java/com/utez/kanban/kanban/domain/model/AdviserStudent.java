package com.utez.kanban.kanban.domain.model;

public class AdviserStudent {
    private Adviser adviser;
    private Student student;
    private boolean status;

    public AdviserStudent(Adviser adviser, Student student, boolean status) {
        this.adviser = adviser;
        this.student = student;
        this.status = status;
    }

    public AdviserStudent() {
    }

    public Adviser getAdviser() {
        return adviser;
    }

    public void setAdviser(Adviser adviser) {
        this.adviser = adviser;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
