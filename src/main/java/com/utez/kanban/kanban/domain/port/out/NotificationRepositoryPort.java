package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationRepositoryPort {

    void save(Notification notification);

    List<Notification> findByStudentID(Long studentID);

    void markAsRead(Long notificationID);

    Optional<Notification> findById(Long id);
}
