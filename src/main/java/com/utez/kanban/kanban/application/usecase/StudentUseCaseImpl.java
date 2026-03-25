package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.infrastructure.entity.EvidenceEntity;
import com.utez.kanban.kanban.domain.port.in.StudentUseCase;
import com.utez.kanban.kanban.domain.port.out.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StudentUseCaseImpl implements StudentUseCase {

    private final TaskRepositoryPort taskRepository;
    private final StudentRepositoryPort studentRepository;
    private final NotificationRepositoryPort notificationRepository;
    private final AdviserRepositoryPort adviserRepository;
    private final StudentTaskEntityRepositoryPort studentTaskEntityRepository;
    private final BoardRepositoryPort boardRepository;
    private final AdviserStudentRepository adviserStudentRepository;
    private final EvidenceRepositoryPort evidenceRepositoryPort;
    private final EvidenceFileRepositoryPort evidenceFileRepositoryPort;

    public StudentUseCaseImpl(TaskRepositoryPort taskRepository,
                              StudentRepositoryPort studentRepository,
                              NotificationRepositoryPort notificationRepository,
                              AdviserRepositoryPort adviserRepository,
                              StudentTaskEntityRepositoryPort studentTaskEntityRepository,
                              BoardRepositoryPort boardRepository,
                              AdviserStudentRepository adviserStudentRepository,
                              EvidenceRepositoryPort evidenceRepositoryPort,
                              EvidenceFileRepositoryPort evidenceFileRepositoryPort) {
        this.taskRepository = taskRepository;
        this.studentRepository = studentRepository;
        this.notificationRepository = notificationRepository;
        this.adviserRepository = adviserRepository;
        this.studentTaskEntityRepository = studentTaskEntityRepository;
        this.boardRepository = boardRepository;
        this.adviserStudentRepository = adviserStudentRepository;
        this.evidenceRepositoryPort = evidenceRepositoryPort;
        this.evidenceFileRepositoryPort = evidenceFileRepositoryPort;
    }

    @Override
    public boolean assignTaskToStudent(Long studentId, Long taskId) {
        return studentTaskEntityRepository.assignTask(studentId, taskId);
    }

    @Override
    public List<Task> getTasksByStudent(Long studentId) {
        return studentTaskEntityRepository.findTasksByStudentId(studentId);
    }

    @Override
    public List<Notification> getNotifications(Long studentId) {
        return notificationRepository.findByStudentId(studentId);
    }

    @Override
    public boolean markNotificationAsRead(Long notificationId) {
        var notification = notificationRepository.findById(notificationId).orElse(null);
        if (notification == null) return false;

        notification.setRead(true);
        notificationRepository.save(notification);
        return true;
    }

    @Override
    public boolean changeTaskStatus(Long studentId, Long taskId, String status) {
        return studentTaskEntityRepository.changeTaskStatus(studentId, taskId, status);
    }

    @Override
    public boolean updateStudent(Long studentId, String name, String lastName, String gender) {
        var student = studentRepository.findById(studentId).orElse(null);
        if (student == null) return false;

        student.setName(name); // alias: firstName
        student.setLastName(lastName);
        student.setGender(gender);

        studentRepository.saveStudent(student);
        return true;
    }

    @Override
    public List<Adviser> getAdvisersByStudent(Long studentId) {
        return adviserStudentRepository
                .getAdvisersByStudent(studentId)
                .stream()
                .filter(adviser -> adviser.getUser() != null && adviser.getUser().isStatus())
                .toList();
    }

    @Override
    public boolean uploadEvidence(Long studentId, Long taskId, MultipartFile file) {
        if (studentId == null || taskId == null || file == null || file.isEmpty()) return false;

        // Aseguramos que el estudiante tiene esa tarea asignada
        if (!studentTaskEntityRepository.existsStudentTask(studentId, taskId)) return false;

        try {
            EvidenceEntity evidence = new EvidenceEntity();
            evidence.setComment(null);
            evidence.setStudentID(studentId);
            evidence.setTaskID(taskId);

            EvidenceEntity created = evidenceRepositoryPort.save(evidence);
            if (created == null || created.getEvidenceID() == null) return false;

            evidenceFileRepositoryPort.saveFile(
                    created.getEvidenceID(),
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Task> getTasksByBoardForStudent(Long studentId, Long adviserId) {
        var board = boardRepository.findBoardByAdviserId(adviserId).orElse(null);
        if (board == null) return List.of();

        // Solo permitir si el adviser está activo para ese estudiante
        if (!adviserStudentRepository.isAdviserActiveForStudent(adviserId, studentId)) {
            return List.of();
        }

        return studentTaskEntityRepository.findTasksByStudentIdAndBoardId(studentId, board.getBoardID());
    }
}