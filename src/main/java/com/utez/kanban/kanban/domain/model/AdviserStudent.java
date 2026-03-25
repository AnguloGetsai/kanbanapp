package com.utez.kanban.kanban.domain.model;

public class AdviserStudent {

    private Long adviserId;
    private Long studentId;
    private boolean status;

    public AdviserStudent() {}

    public AdviserStudent(Adviser adviser, Student student, boolean status) {
        if (adviser != null) {
            this.adviserId = adviser.getAdviserID();
        }
        if (student != null) {
            this.studentId = student.getStudentID();
        }
        this.status = status;
    }

    // GETTERS Y SETTERS

    public Long getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(Long adviserId) {
        this.adviserId = adviserId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}