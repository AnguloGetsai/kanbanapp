package com.utez.kanban.kanban.application.student.service;

import com.utez.kanban.kanban.domain.student.model.Student;
import com.utez.kanban.kanban.domain.student.port.in.EstablishingInformationUseCase;

public class StudentService implements EstablishingInformationUseCase {
    private EstablishingInformationUseCase establishingInformationUseCase;

    public StudentService(EstablishingInformationUseCase establishingInformationUseCase){
        this.establishingInformationUseCase = establishingInformationUseCase;
    }

    @Override
    public boolean establishingInformation(Student student) {
        return establishingInformationUseCase.establishingInformation(student);
    }
}
