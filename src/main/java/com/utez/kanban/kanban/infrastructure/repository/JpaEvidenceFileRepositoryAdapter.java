package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.EvidenceFile;
import com.utez.kanban.kanban.domain.port.out.EvidenceFileRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.EvidenceEntity;
import com.utez.kanban.kanban.infrastructure.entity.EvidenceFileEntity;
import com.utez.kanban.kanban.infrastructure.mapper.EvidenceFileMapper;
import com.utez.kanban.kanban.infrastructure.mapper.EvidenceMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaEvidenceFileRepositoryAdapter implements EvidenceFileRepositoryPort {
    private final JpaEvidenceFileRepository jpaEvidenceFileRepository;

    public JpaEvidenceFileRepositoryAdapter(JpaEvidenceFileRepository jpaEvidenceFileRepository){
        this.jpaEvidenceFileRepository =jpaEvidenceFileRepository;
    }

    @Override
    public void saveAll(List<EvidenceFile> files) {

        if(files == null || files.isEmpty()) return;


        EvidenceEntity evidenceEntity = EvidenceMapper.toEntity(
                files.get(0).getEvidence()
        );

        List<EvidenceFileEntity> entities = files.stream()
                .map(file -> EvidenceFileMapper.toEntity(file, evidenceEntity))
                .toList();

        jpaEvidenceFileRepository.saveAll(entities);
    }
}
