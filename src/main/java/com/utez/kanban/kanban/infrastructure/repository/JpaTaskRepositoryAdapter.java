package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Task;
import com.utez.kanban.kanban.domain.port.out.TaskRepositoryPort;
import com.utez.kanban.kanban.infrastructure.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaTaskRepositoryAdapter implements TaskRepositoryPort {

    private final JpaTaskRepository jpaTaskRepository;

    public JpaTaskRepositoryAdapter(JpaTaskRepository jpaTaskRepository){
        this.jpaTaskRepository = jpaTaskRepository;
    }


    @Override
    public Optional<Task> save(Task task) {
        return  Optional.of(TaskMapper.toTask(jpaTaskRepository.save(TaskMapper.toTaskEntity(task))));
    }
}
