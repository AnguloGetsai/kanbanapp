package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Evidence;
import com.utez.kanban.kanban.domain.model.EvidenceFile;
import com.utez.kanban.kanban.infrastructure.entity.EvidenceEntity;

import java.util.ArrayList;
import java.util.List;

public class EvidenceMapper {
    public static Evidence toDomain(EvidenceEntity entity){

        List<EvidenceFile> files = new ArrayList<>();

        if(entity.getEvidenceFiles() != null){
            files = entity.getEvidenceFiles()
                    .stream()
                    .map(EvidenceFileMapper::toDomain)
                    .toList();
        }

        return new Evidence(
                entity.getEvidenceID(),
                entity.getUploadDate(),
                entity.getComment(),
                StudentTaskMapper.toStudentTask(entity.getStudentTaskEntity()),
                files
        );
    }

    public static EvidenceEntity toEntity(Evidence evidence){

        EvidenceEntity entity = new EvidenceEntity();
        entity.setEvidenceID(evidence.getEvidenceID());
        entity.setUploadDate(evidence.getUploadDate());
        entity.setComment(evidence.getComment());

        entity.setStudentTaskEntity(
                StudentTaskMapper.toStudentTaskEntity(evidence.getStudentTask())
        );

        return entity;
    }
}
