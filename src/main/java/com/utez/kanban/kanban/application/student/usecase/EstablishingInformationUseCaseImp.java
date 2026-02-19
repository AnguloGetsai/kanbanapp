package com.utez.kanban.kanban.application.student.usecase;

import com.utez.kanban.kanban.domain.student.model.Student;
import com.utez.kanban.kanban.domain.student.port.in.EstablishingInformationUseCase;
import com.utez.kanban.kanban.domain.student.port.out.StudentRepositoryPort;

public class EstablishingInformationUseCaseImp implements EstablishingInformationUseCase {
    private final StudentRepositoryPort studentRepositoryPort;

    public EstablishingInformationUseCaseImp(StudentRepositoryPort studentRepositoryPort){
        this.studentRepositoryPort = studentRepositoryPort;
    }


    @Override
    public boolean establishingInformation(Student student) {
        return studentRepositoryPort.establishInformation(student);
    }
}
