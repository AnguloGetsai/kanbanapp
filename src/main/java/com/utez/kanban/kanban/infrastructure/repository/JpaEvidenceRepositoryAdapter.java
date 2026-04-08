package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Evidence;
import com.utez.kanban.kanban.domain.model.StudentTask;
import com.utez.kanban.kanban.domain.port.out.EvidenceRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.EvidenceEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentTaskEntity;
import com.utez.kanban.kanban.infrastructure.mapper.EvidenceMapper;
import com.utez.kanban.kanban.infrastructure.mapper.StudentTaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<Evidence> findByStudentTask(StudentTask studentTask) {

        StudentTaskEntity entity = StudentTaskMapper.toStudentTaskEntity(studentTask);

        return jpaEvidenceRepository.findByStudentTaskEntity(entity)
                .stream()
                .map(EvidenceMapper::toDomain)
                .toList();
    }
}
