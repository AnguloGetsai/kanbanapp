package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.model.StudentTask;
import com.utez.kanban.kanban.domain.port.out.StudentTaskRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentTaskEntity;
import com.utez.kanban.kanban.infrastructure.mapper.StudentMapper;
import com.utez.kanban.kanban.infrastructure.mapper.StudentTaskMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;

@Component
public class JpaStudentTaskRepositoryAdapter implements StudentTaskRepositoryPort {

    private final JpaStudentTaskRepository jpaStudentTaskRepository;
    public JpaStudentTaskRepositoryAdapter(JpaStudentTaskRepository jpaStudentTaskRepository){
        this.jpaStudentTaskRepository = jpaStudentTaskRepository;
    }


    @Override
    public void save(StudentTask studentTask) {
        jpaStudentTaskRepository.save(StudentTaskMapper.toStudentTaskEntity(studentTask));
    }

    @Override
    public void saveAll(List<StudentTask> studentTaskList) {

        List<StudentTaskEntity> studentTaskEntityList = studentTaskList
                .stream()
                .map(studentTask ->
                        StudentTaskMapper.toStudentTaskEntity(studentTask)
                )
                .toList();

        jpaStudentTaskRepository.saveAll(studentTaskEntityList);
    }
}
