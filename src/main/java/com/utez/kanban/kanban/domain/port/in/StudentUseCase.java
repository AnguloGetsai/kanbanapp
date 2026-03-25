package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Notification;
import com.utez.kanban.kanban.domain.model.Task;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentUseCase {



    boolean assignTaskToStudent(Long studentId, Long taskId);

    List<Task> getTasksByStudent(Long studentId);

    List<Notification> getNotifications(Long studentId);

    boolean markNotificationAsRead(Long notificationId);

    boolean changeTaskStatus(Long studentId, Long taskId, String status);

    boolean updateStudent(Long studentId, String name, String lastName, String gender);

    List<Adviser> getAdvisersByStudent(Long studentId);

    List<Task> getTasksByBoardForStudent(Long studentId, Long adviserId);

    boolean uploadEvidence(Long studentId, Long taskId, MultipartFile file);
}