package com.utez.kanban.kanban.domain.model;

public class Notification {
    private Long notificationID;
    private boolean isRead;
    private String message;

    private Student student;


    public Notification(Long notificationID, boolean isRead,
                        String message, Student student) {
        this.notificationID = notificationID;
        this.isRead = isRead;
        this.message = message;
        this.student = student;
    }
    public Notification(){

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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
