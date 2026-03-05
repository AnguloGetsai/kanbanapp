package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentTaskID implements Serializable {
    private Long studentID;
    private Long taskID;

    public StudentTaskID(Long studentID, Long taskID) {
        this.studentID = studentID;
        this.taskID = taskID;
    }

    public StudentTaskID() {
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }


    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if( !(o instanceof  StudentTaskID)) return false;
        StudentTaskID that = (StudentTaskID) o;
        return Objects.equals(studentID, that.studentID) && Objects.equals(taskID, that.taskID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, taskID);
    }
}
