package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Task;
import com.utez.kanban.kanban.domain.port.out.TaskRepositoryPort;
import com.utez.kanban.kanban.infrastructure.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JpaTaskRepositoryAdapter implements TaskRepositoryPort {

    private final JpaTaskRepository jpaTaskRepository;

    public JpaTaskRepositoryAdapter(JpaTaskRepository jpaTaskRepository) {
        this.jpaTaskRepository = jpaTaskRepository;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return jpaTaskRepository.findById(id)
                .map(TaskMapper::toTask);
    }

    @Override
    public Task save(Task task) {
        return TaskMapper.toTask(
                jpaTaskRepository.save(TaskMapper.toEntity(task))
        );
    }

    @Override
    public List<Task> getTasksByStudent(Long studentId) {
        return jpaTaskRepository.findByStudent_StudentID(studentId)
                .stream()
                .map(TaskMapper::toTask)
                .toList();
    }
}