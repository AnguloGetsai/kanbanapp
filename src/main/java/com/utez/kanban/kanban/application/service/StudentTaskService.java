package com.utez.kanban.kanban.application.service;

import com.utez.kanban.kanban.application.usecase.StudentTaskUseCaseImp;
import com.utez.kanban.kanban.domain.model.StudentTask;
import com.utez.kanban.kanban.domain.port.in.StudentTaskUseCase;

import java.util.List;

public class StudentTaskService implements StudentTaskUseCase {
    private StudentTaskUseCase studentTaskUseCase;

    public StudentTaskService(StudentTaskUseCase studentTaskUseCase){
        this.studentTaskUseCase = studentTaskUseCase;
    }


    @Override
    public List<StudentTask> findByTaskId(Long taskId) {
        return studentTaskUseCase.findByTaskId(taskId);
    }
}
