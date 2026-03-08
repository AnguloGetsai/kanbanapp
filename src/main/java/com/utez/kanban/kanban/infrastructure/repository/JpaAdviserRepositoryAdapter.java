package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.port.out.AdviserRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.AdviserEntity;
import com.utez.kanban.kanban.infrastructure.mapper.AdviserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class JpaAdviserRepositoryAdapter implements AdviserRepositoryPort {
    private final JpaAdviserRepository jpaAdviserRepository;

    public JpaAdviserRepositoryAdapter(JpaAdviserRepository jpaAdviserRepository){
        this.jpaAdviserRepository = jpaAdviserRepository;
    }

    @Override
    public void saveAdviser(Adviser adviser) {
        jpaAdviserRepository.save(AdviserMapper.toAdviserEntity(adviser));
    }



    @Override
    public List<Adviser> getAllAdvisers() {
        return jpaAdviserRepository.findAll()
                .stream()
                .map(AdviserMapper::toAdviser)
                .toList();
    }




}
