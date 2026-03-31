package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Notification;
import com.utez.kanban.kanban.infrastructure.entity.NotificationEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;

public class NotificationMapper {
    public static Notification toNotification(NotificationEntity entity){
        Notification notification = new Notification();

        notification.setNotificationID(entity.getNotificationID());
        notification.setRead(entity.isRead());
        notification.setMessage(entity.getMessage());
        notification.setStudentID(entity.getStudentEntity().getStudentID());

        return notification;
    }

    public static NotificationEntity toNotificationEntity(Notification notification){
        NotificationEntity entity = new NotificationEntity();

        entity.setNotificationID(notification.getNotificationID());
        entity.setRead(notification.isRead());
        entity.setMessage(notification.getMessage());

        StudentEntity student = new StudentEntity();
        student.setStudentID(notification.getStudentID());

        entity.setStudentEntity(student);

        return entity;
    }
}
