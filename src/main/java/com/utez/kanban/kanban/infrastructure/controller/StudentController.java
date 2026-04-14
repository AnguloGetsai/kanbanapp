package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.StudentService;
import com.utez.kanban.kanban.domain.model.Notification;
import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.model.StudentTask;
import com.utez.kanban.kanban.domain.port.out.NotificationRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.StudentRepositoryPort;
import com.utez.kanban.kanban.infrastructure.controller.DTO.*;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepositoryPort studentRepositoryPort;
    private final NotificationRepositoryPort notificationRepositoryPort;

    public StudentController(
            StudentService studentService,
            StudentRepositoryPort studentRepositoryPort,
            NotificationRepositoryPort notificationRepositoryPort

    ){
        this.studentService = studentService;
        this.notificationRepositoryPort = notificationRepositoryPort;
        this.studentRepositoryPort = studentRepositoryPort;
    }


    @GetMapping("/advisers")
    public List<AdviserInformation> getMyAdvisers(Authentication authentication){

        String email = authentication.getName();

        return studentService.getMyAdvisers(email)
                .stream()
                .map(AdviserInformation::toAdviserInformation)
                .collect(Collectors.toList());
    }

    @PutMapping("/updateName")
    public ResponseEntity<?> updateName(
            @RequestBody UpdateStudentNameDto dto,
            Authentication authentication
    ){
        try {
            studentService.updateStudentName(
                    authentication.getName(),
                    dto.getFirstName(),
                    dto.getLastName()
            );

            return ResponseEntity.ok(new SuccessResponse(200, "Name updated"));

        } catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @PostMapping("/uploadLogo")
    public ResponseEntity<?> uploadLogo(
            @RequestParam("file") MultipartFile file,
            Authentication authentication
    ){
        try {
            studentService.updateStudentImage(
                    authentication.getName(),
                    file.getBytes()
            );

            return ResponseEntity.ok(new SuccessResponse(200, "logo updated"));

        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @GetMapping("/profile")
    public ResponseEntity<?> getStudentProfile(Authentication authentication){

        Student student = studentService.getStudentInformation(authentication.getName())
                .orElseThrow(() -> new RuntimeException("STUDENT NOT FOUND"));

        return ResponseEntity.ok(StudentProfileDto.fromDomain(student));
    }







    @GetMapping("/tasks/{adviserID}")
    public ResponseEntity<?> getTasksByAdviser(
            @PathVariable Long adviserID,
            Authentication authentication
    ){
        try {
            String email = authentication.getName();

            return ResponseEntity.ok(
                    studentService.getTasksByAdviser(email, adviserID)
                            .stream()
                            .map(StudentTaskDto::fromDomain)
                            .toList()
            );
        } catch (com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException e) {

            return ResponseEntity.status(403).body(Map.of(
                    "status", 403,
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "status", 500,
                    "message", "Ocurrió un error al obtener las tareas"
            ));
        }
    }


    @GetMapping("/task/{taskID}")
    public ResponseEntity<?> getTaskDetail(
            @PathVariable Long taskID,
            Authentication authentication
    ){

        String email = authentication.getName();

        StudentTask task = studentService.getTaskDetail(email, taskID)
                .orElseThrow(() -> new RuntimeException("TASK NOT FOUND"));

        return ResponseEntity.ok(TaskDetailDto.fromDomain(task));
    }


    @GetMapping("/notifications")
    public ResponseEntity<?> getNotifications(Authentication authentication){

        Student student = studentRepositoryPort.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Notification> notifications =
                notificationRepositoryPort.findByStudentID(student.getStudentID());

        return ResponseEntity.ok(Map.of(
                "status",200,
                "data",notifications
        ));
    }


    @PutMapping("/notifications/{id}/read")
    public ResponseEntity<?> markAsRead(
            @PathVariable Long id,
            Authentication authentication
    ){

        Student student = studentRepositoryPort.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Notification notification = notificationRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));


        if(!notification.getStudentID().equals(student.getStudentID())){
            throw new RuntimeException("Unauthorized");
        }

        notificationRepositoryPort.markAsRead(id);

        return ResponseEntity.ok(Map.of(
                "status",200,
                "message","Notification marked as read"
        ));
    }

    @PostMapping(value = "/submitEvidence", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> submitEvidence(
            @ModelAttribute SubmitEvidenceDTO dto,
            Authentication authentication
    ){
        try{
            studentService.submitEvidence(
                    authentication.getName(),
                    dto.getTaskID(),
                    dto.getComment(),
                    dto.getFiles()
            );

            return ResponseEntity.ok(new SuccessResponse(200, "Evidence submitted"));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/task/{taskID}/status")
    public ResponseEntity<?> changeTaskStatus(
            @PathVariable Long taskID,
           @Valid @RequestBody ChangeStatusDTO dto,
            Authentication authentication
    ){
        try {

            studentService.changeTaskStatus(
                    authentication.getName(),
                    taskID,
                    dto.getStatus()
            );

            return ResponseEntity.ok(
                    new SuccessResponse(200, "Task status updated")
            );

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
