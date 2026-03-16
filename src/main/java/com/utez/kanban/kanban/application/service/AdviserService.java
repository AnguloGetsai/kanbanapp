package com.utez.kanban.kanban.application.service;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.port.in.AdviserUseCase;

import java.util.List;
import java.util.Optional;

public class AdviserService implements AdviserUseCase {
    private final AdviserUseCase adviserUseCase;

    public AdviserService(AdviserUseCase adviserUseCase){
        this.adviserUseCase = adviserUseCase;
    }
    @Override
    public void registerStudent(String email, String firstName, String lastName) {
        adviserUseCase.registerStudent(email, firstName, lastName);
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


}
