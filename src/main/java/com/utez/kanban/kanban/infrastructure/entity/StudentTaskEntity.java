package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "student_task")
public class StudentTaskEntity {
    @EmbeddedId
    private StudentTaskID id;

    @ManyToOne
    @MapsId("studentID")
    @JoinColumn(name = "studentID")
    private StudentEntity studentEntity;

    @ManyToOne
    @MapsId("taskID")
    @JoinColumn(name = "taskID")
    private TaskEntity taskEntity;

    private String color;
    private String status;
    private LocalDate assignedDate;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    public StudentTaskID getId() {
        return id;
    }

    public void setId(StudentTaskID id) {
        this.id = id;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }


}
