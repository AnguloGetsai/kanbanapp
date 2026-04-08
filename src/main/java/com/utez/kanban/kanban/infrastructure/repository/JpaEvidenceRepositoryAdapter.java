package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Evidence;
import com.utez.kanban.kanban.domain.port.out.EvidenceRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.EvidenceEntity;
import com.utez.kanban.kanban.infrastructure.mapper.EvidenceMapper;
import org.springframework.stereotype.Component;

@Component
public class JpaEvidenceRepositoryAdapter implements EvidenceRepositoryPort {
    private final JpaEvidenceRepository jpaEvidenceRepository;

    public JpaEvidenceRepositoryAdapter(JpaEvidenceRepository jpaEvidenceRepository){
        this.jpaEvidenceRepository = jpaEvidenceRepository;
    }

    @Override
    public Evidence save(Evidence evidence) {

        EvidenceEntity entity = EvidenceMapper.toEntity(evidence);

        EvidenceEntity saved = jpaEvidenceRepository.save(entity);

        return EvidenceMapper.toDomain(saved);
    }
}
