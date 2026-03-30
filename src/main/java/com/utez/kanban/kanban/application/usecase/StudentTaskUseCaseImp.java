package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.StudentTask;
import com.utez.kanban.kanban.domain.port.in.StudentTaskUseCase;
import com.utez.kanban.kanban.domain.port.out.StudentTaskRepositoryPort;

import java.security.PublicKey;
import java.util.List;

public class StudentTaskUseCaseImp implements StudentTaskUseCase {
    private final StudentTaskRepositoryPort studentTaskRepositoryPort;
    public StudentTaskUseCaseImp(StudentTaskRepositoryPort studentTaskRepositoryPort){
        this.studentTaskRepositoryPort = studentTaskRepositoryPort;
    }


    @Override
    public List<StudentTask> findByTaskId(Long taskId) {
        return studentTaskRepositoryPort.findByTaskId(taskId);
    }
}
