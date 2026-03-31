package com.utez.kanban.kanban.domain.model;

public class Notification {
    private Long notificationID;
    private boolean isRead;
    private String message;

    private Long studentID;

    public Notification(Long notificationID, boolean isRead, String message, Long studentID) {
        this.notificationID = notificationID;
        this.isRead = isRead;
        this.message = message;
        this.studentID = studentID;
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

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }
}
