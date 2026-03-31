package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Notification;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.port.out.NotificationRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.NotificationEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;
import com.utez.kanban.kanban.infrastructure.mapper.NotificationMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaNotificationRepositoryAdapter implements NotificationRepositoryPort {

    private final JpaNotificationRepository repository;

    public JpaNotificationRepositoryAdapter(JpaNotificationRepository repository){
        this.repository = repository;
    }

    @Override
    public void save(Notification notification){
        NotificationEntity entity = new NotificationEntity();
        entity.setMessage(notification.getMessage());
        entity.setRead(notification.isRead());

        StudentEntity student = new StudentEntity();
        student.setStudentID(notification.getStudentID());

        entity.setStudentEntity(student);

        repository.save(entity);
    }

    @Override
    public List<Notification> findByStudentID(Long studentID){
        return repository.findByStudentEntity_StudentID(studentID)
                .stream()
                .map(e -> {
                    Notification n = new Notification();
                    n.setNotificationID(e.getNotificationID());
                    n.setMessage(e.getMessage());
                    n.setRead(e.isRead());
                    n.setStudentID(e.getStudentEntity().getStudentID());
                    return n;
                }).toList();
    }

    @Override
    public void markAsRead(Long notificationID){
        NotificationEntity entity = repository.findById(notificationID).orElseThrow();
        entity.setRead(true);
        repository.save(entity);
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return repository.findById(id)
                .map(NotificationMapper::toNotification);
    }
}
