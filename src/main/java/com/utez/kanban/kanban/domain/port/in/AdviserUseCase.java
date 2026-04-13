package com.utez.kanban.kanban.domain.port.in;


import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.infrastructure.controller.DTO.AdviserReportDto;
import com.utez.kanban.kanban.infrastructure.controller.DTO.StudentExpedienteDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AdviserUseCase {
    void registerStudent(String adviserEmail,String email, String firstName, String lastName);
    void addStudentToBoard(String adviserEmail,String studentEmail);
    List<Student> getAllStudents(String email);
    void disableBoardStudent(String email, Long studentID);
    void enableBoardStudent(String email, Long studentID);
    void uploadLogo(String email, byte[] logo);
    void updateAdviserInformation(String email, Adviser adviser);
    Optional<Adviser> getAdviserInformation(String email);
    void createTask(List<Long> studentIDs, Task task, String email, List<Attachment> files);
    List<Task> getAllTasks(String email);
    void deleteTask(Long taskID, String email);
    void updateTask(Long taskID, String email, Task task,
                    List<Long> studentIDs, List<Attachment> files);
    void updateTaskStatus(Long taskID, String email, String status);
    List<Evidence> getStudentEvidences(String email, Long taskID, Long studentID);
    void gradeStudentTask(String email, Long taskID, Long studentID, Double grade, String feedback);
    AdviserReportDto getAdviserReport(String email, LocalDate startDate, LocalDate endDate);
    StudentExpedienteDto getStudentExpediente(String email, Long studentID, LocalDate startDate, LocalDate endDate);
    AdviserReportDto getAdviserReportById(Long adviserID, LocalDate startDate, LocalDate endDate);

}
