package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Notification;
import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.infrastructure.entity.NotificationEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {
    public static Student toStudent(StudentEntity studentEntity){
        List<Notification> notificationList = new ArrayList<>();
        for(NotificationEntity notificationEntity: studentEntity.getNotifications()){
            notificationList.add(NotificationMapper.toNotification(notificationEntity));
        }
        return new Student(
                studentEntity.getStudentID(),
                studentEntity.getFirstName(),
                studentEntity.getLastName(),
                studentEntity.getGender(),
                studentEntity.getImage(),
                UserMapper.toUser(studentEntity.getUserEntity()),
                notificationList
        );
    }

    public static StudentEntity toStudentEntity(Student student){
        List<NotificationEntity> notificationEntityList = new ArrayList<>();
        if(student.getNotifications() != null){
            for(Notification notification: student.getNotifications()){
                notificationEntityList.add(NotificationMapper.toNotificationEntity(notification));
            }
        }

        return new StudentEntity(
                student.getStudentID(),
                student.getFirstName(),
                student.getLastName(),
                student.getGender(),
                student.getImage(),
                UserMapper.toUserEntity(student.getUser()),
                notificationEntityList
        );
    }
}
