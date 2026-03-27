package com.utez.kanban.kanban.application.service;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Attachment;
import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.model.Task;
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
    public void updateTask(Long taskID, String email, Task updatedTask) {
        adviserUseCase.updateTask(taskID, email, updatedTask);
    }

    @Override
    public void updateTaskStatus(Long taskID, String email, String status) {
        adviserUseCase.updateTaskStatus(taskID, email, status);
    }


}
