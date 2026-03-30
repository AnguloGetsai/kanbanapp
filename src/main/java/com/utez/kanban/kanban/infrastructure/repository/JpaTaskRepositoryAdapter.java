package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Task;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.port.out.TaskRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.TaskEntity;
import com.utez.kanban.kanban.infrastructure.mapper.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Override
    public List<Task> findTasksByAdviserID(Long adviserID) {
        return jpaTaskRepository.findTasksByAdviserID(adviserID)
                .stream()
                .map(TaskMapper::toTask)
                .toList();
    }

    @Override
    public void deleteTask(Long taskID) {
        jpaTaskRepository.deleteById(taskID);
    }

    @Override
    public Optional<Task> findById(Long id) {
        TaskEntity taskEntity = jpaTaskRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleViolationException("task not found"));
        return Optional.of(TaskMapper.toTask(taskEntity));
    }

    @Override
    public void update(Task task) {
        TaskEntity entity = TaskMapper.toTaskEntity(task);
        jpaTaskRepository.save(entity);
    }


}
