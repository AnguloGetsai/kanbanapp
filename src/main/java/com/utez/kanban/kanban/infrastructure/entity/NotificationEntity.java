package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationID;
    private boolean isRead;
    private String message;

    @ManyToOne
    @JoinColumn(name = "studentID")
    private StudentEntity studentEntity;


    public NotificationEntity() {
    }


    public NotificationEntity(Long notificationID, boolean isRead, String message, StudentEntity studentEntity) {
        this.notificationID = notificationID;
        this.isRead = isRead;
        this.message = message;
        this.studentEntity = studentEntity;
    }

    public Long getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(Long notificationID) {
        this.notificationID = notificationID;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public void setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
    }
}
