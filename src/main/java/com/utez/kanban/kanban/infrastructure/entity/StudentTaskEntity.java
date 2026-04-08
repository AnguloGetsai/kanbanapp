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

    // private String color; campo eliminado

    private String status;
    private LocalDate assignedDate;
    private LocalDate culminationDate;

    private Double grade;
    private String feedback;


    public StudentTaskEntity(StudentTaskID id, StudentEntity studentEntity,
                             TaskEntity taskEntity, String status, LocalDate assignedDate,
                             LocalDate culminationDate, Double grade, String feedback) {
        this.id = id;
        this.studentEntity = studentEntity;
        this.taskEntity = taskEntity;
        this.status = status;
        this.assignedDate = assignedDate;
        this.culminationDate = culminationDate;
        this.grade = grade;
        this.feedback = feedback;
    }

    public StudentTaskEntity(){}
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

    public LocalDate getCulminationDate() {
        return culminationDate;
    }

    public void setCulminationDate(LocalDate culminationDate) {
        this.culminationDate = culminationDate;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
