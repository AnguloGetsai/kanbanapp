package com.utez.kanban.kanban.application.service;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.model.StudentTask;
import com.utez.kanban.kanban.domain.port.in.StudentUseCase;

import java.util.List;
import java.util.Optional;

public class StudentService implements StudentUseCase {

    private final StudentUseCase studentUseCase;

    public StudentService(StudentUseCase studentUseCase){
        this.studentUseCase = studentUseCase;
    }


    @Override
    public List<Adviser> getMyAdvisers(String email) {
        return studentUseCase.getMyAdvisers(email);
    }

    @Override
    public void updateStudentName(String email, String firstName, String lastName) {
     studentUseCase.updateStudentName(email, firstName, lastName);
    }

    @Override
    public void updateStudentImage(String email, byte[] image) {
        studentUseCase.updateStudentImage(email, image);
    }

    @Override
    public Optional<Student> getStudentInformation(String email) {
        return studentUseCase.getStudentInformation(email);
    }

    @Override
    public List<StudentTask> getTasksByAdviser(String email, Long adviserID) {
        return studentUseCase.getTasksByAdviser(email, adviserID);
    }

    @Override
    public Optional<StudentTask> getTaskDetail(String email, Long taskID) {
        return studentUseCase.getTaskDetail(email, taskID);
    }
}
