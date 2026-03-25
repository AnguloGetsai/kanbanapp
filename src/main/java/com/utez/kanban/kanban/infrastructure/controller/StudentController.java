package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.domain.model.Notification;
import com.utez.kanban.kanban.domain.model.Task;
import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.port.in.StudentUseCase;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentUseCase studentUseCase;

    public StudentController(StudentUseCase studentUseCase) {
        this.studentUseCase = studentUseCase;
    }

    @PostMapping("/{studentId}/assign-task/{taskId}")
    public boolean assignTask(@PathVariable Long studentId, @PathVariable Long taskId) {
        return studentUseCase.assignTaskToStudent(studentId, taskId);
    }

    @GetMapping("/{studentId}/tasks")
    public List<Task> getTasks(@PathVariable Long studentId) {
        return studentUseCase.getTasksByStudent(studentId);
    }

    @GetMapping("/{studentId}/notifications")
    public List<Notification> getNotifications(@PathVariable Long studentId) {
        return studentUseCase.getNotifications(studentId);
    }

    @PutMapping("/notification/{notificationId}/read")
    public boolean markAsRead(@PathVariable Long notificationId) {
        return studentUseCase.markNotificationAsRead(notificationId);
    }

    // 1) Cambiar estado (ToDo / Doing / Done) de la tarea del estudiante
    @PutMapping("/{studentId}/tasks/{taskId}/status")
    public boolean changeTaskStatus(
            @PathVariable Long studentId,
            @PathVariable Long taskId,
            @RequestParam("status") String status
    ) {
        if (status == null) return false;
        String normalized = status.trim();
        if (normalized.equalsIgnoreCase("ToDo")) normalized = "ToDo";
        else if (normalized.equalsIgnoreCase("Doing")) normalized = "Doing";
        else if (normalized.equalsIgnoreCase("Done")) normalized = "Done";
        else return false;

        return studentUseCase.changeTaskStatus(studentId, taskId, normalized);
    }

    // 2) Actualizar información del estudiante (solo nombre, apellido y género)
    @PutMapping("/{studentId}/profile")
    public boolean updateStudent(
            @PathVariable Long studentId,
            @RequestBody StudentProfileUpdateRequest request
    ) {
        return studentUseCase.updateStudent(
                studentId,
                request.getFirstName(),
                request.getLastName(),
                request.getGender()
        );
    }

    // 3) Obtener asesores registrados para el estudiante (solo con acceso activo)
    @GetMapping("/{studentId}/advisers")
    public List<Adviser> getAdvisers(@PathVariable Long studentId) {
        return studentUseCase.getAdvisersByStudent(studentId);
    }

    // 4) Cambiar tablero: al seleccionar adviser, se devuelven sus tareas con estado ToDo/Doing/Done
    @GetMapping("/{studentId}/advisers/{adviserId}/tasks")
    public List<Task> getTasksByBoard(
            @PathVariable Long studentId,
            @PathVariable Long adviserId
    ) {
        return studentUseCase.getTasksByBoardForStudent(studentId, adviserId);
    }

    // 6) Entregar la tarea subiendo evidencia (PDF/imagen/etc.)
    @PostMapping("/{studentId}/tasks/{taskId}/upload")
    public boolean uploadEvidence(
            @PathVariable Long studentId,
            @PathVariable Long taskId,
            @RequestParam("file") MultipartFile file
    ) {
        return studentUseCase.uploadEvidence(studentId, taskId, file);
    }

    // DTO simple para actualizar perfil del estudiante
    public static class StudentProfileUpdateRequest {
        private String firstName;
        private String lastName;
        private String gender;

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }
        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }
        public String getGender() { return gender; }
        public void setGender(String gender) { this.gender = gender; }
    }
}