package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Notification;
import com.utez.kanban.kanban.infrastructure.entity.NotificationEntity;

public class NotificationMapper {
    public static Notification toNotification(NotificationEntity notificationEntity){
        return new Notification(
                notificationEntity.getNotificationID(),
                notificationEntity.isRead(),
                notificationEntity.getMessage(),
                StudentMapper.toStudent(notificationEntity.getStudentEntity())
        );
    }


    public static NotificationEntity toNotificationEntity(Notification notification){
        return new NotificationEntity(
                notification.getNotificationID(),
                notification.isRead(),
                notification.getMessage(),
                StudentMapper.toStudentEntity(notification.getStudent())
        );
    }
}
