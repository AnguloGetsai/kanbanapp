package com.utez.kanban.kanban.application.service;

import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.domain.port.in.AdviserUseCase;

import java.util.List;
import java.util.Optional;

public class AdviserService implements AdviserUseCase {
    private final AdviserUseCase adviserUseCase;

    public AdviserService(AdviserUseCase adviserUseCase){
        this.adviserUseCase = adviserUseCase;
    }
    @Override
    public void registerStudent(String adviserEmail,String email, String firstName, String lastName) {
        adviserUseCase.registerStudent(adviserEmail,email, firstName, lastName);
    }

    @Override
    public void addStudentToBoard(String adviserEmail, String studentEmail) {
        adviserUseCase.addStudentToBoard(adviserEmail, studentEmail);
    }

    @Override
    public List<Student> getAllStudents(String email) {
        return adviserUseCase.getAllStudents(email);
    }

    @Override
    public void disableBoardStudent(String email, Long studentID) {
        adviserUseCase.disableBoardStudent(email, studentID);
    }

    @Override
    public void enableBoardStudent(String email, Long studentID) {
        adviserUseCase.enableBoardStudent(email, studentID);
    }

    @Override
    public void uploadLogo(String email, byte[] logo) {
        adviserUseCase.uploadLogo(email, logo);
    }

    @Override
    public void updateAdviserInformation(String email, Adviser adviser) {
        adviserUseCase.updateAdviserInformation(email, adviser);
    }

    @Override
    public Optional<Adviser> getAdviserInformation(String email) {
        return adviserUseCase.getAdviserInformation(email);
    }

    @Override
    public void createTask(List<Long> studentIDs, Task task, String email, List<Attachment> files) {
        adviserUseCase.createTask(studentIDs, task, email, files);
    }

    @Override
    public List<Task> getAllTasks(String email) {
        return adviserUseCase.getAllTasks(email);
    }

    @Override
    public void deleteTask(Long taskID, String email) {
        adviserUseCase.deleteTask(taskID, email);
    }

    @Override
    public void updateTask(Long taskID, String email, Task task, List<Long> studentIDs, List<Attachment> files) {
        adviserUseCase.updateTask(taskID, email, task, studentIDs, files);
    }



    @Override
    public void updateTaskStatus(Long taskID, String email, String status) {
        adviserUseCase.updateTaskStatus(taskID, email, status);
    }

    @Override
    public List<Evidence> getStudentEvidences(String email, Long taskID, Long studentID) {
        return adviserUseCase.getStudentEvidences(email, taskID, studentID);
    }

    @Override
    public void gradeStudentTask(String email, Long taskID, Long studentID, Double grade, String feedback) {
        adviserUseCase.gradeStudentTask(email, taskID, studentID, grade, feedback);
    }


}
