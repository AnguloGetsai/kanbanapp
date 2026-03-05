package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AdviserStudentID implements Serializable {

    private Long adviserID;
    private Long studentID;

    public AdviserStudentID(Long adviserID, Long studentID) {
        this.adviserID = adviserID;
        this.studentID = studentID;
    }


    public AdviserStudentID() {
    }


    public Long getAdviserID() {
        return adviserID;
    }

    public void setAdviserID(Long adviserID) {
        this.adviserID = adviserID;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if( !(o instanceof AdviserStudentID)) return false;
        AdviserStudentID that = (AdviserStudentID) o;
        return Objects.equals(studentID, that.studentID) && Objects.equals(adviserID, that.adviserID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adviserID, studentID);
    }
}
