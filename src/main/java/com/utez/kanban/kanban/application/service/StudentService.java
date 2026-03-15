package com.utez.kanban.kanban.application.service;

import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.port.in.StudentUseCase;

public class StudentService implements StudentUseCase {

    private final StudentUseCase studentUseCase;

    public StudentService(StudentUseCase studentUseCase){
        this.studentUseCase = studentUseCase;
    }



}
