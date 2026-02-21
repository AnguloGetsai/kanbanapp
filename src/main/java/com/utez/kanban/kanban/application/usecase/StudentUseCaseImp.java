package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.port.in.StudentUseCase;
import com.utez.kanban.kanban.domain.port.out.StudentRepositoryPort;

public class StudentUseCaseImp implements StudentUseCase {

    private final StudentRepositoryPort studentRepositoryPort;

    public StudentUseCaseImp(StudentRepositoryPort studentRepositoryPort){
        this.studentRepositoryPort = studentRepositoryPort;
    }

    @Override
    public Student saveBasicInformation(Student student) {
        return studentRepositoryPort.saveBasicInformation(student);
    }
}
