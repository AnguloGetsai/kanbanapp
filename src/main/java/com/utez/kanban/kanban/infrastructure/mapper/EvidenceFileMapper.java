package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.EvidenceFile;
import com.utez.kanban.kanban.infrastructure.entity.EvidenceEntity;
import com.utez.kanban.kanban.infrastructure.entity.EvidenceFileEntity;

public class EvidenceFileMapper {
    public static EvidenceFile toDomain(EvidenceFileEntity entity){
        return new EvidenceFile(
                entity.getFileID(),
                entity.getFileName(),
                entity.getFileType(),
                entity.getFileData(),
                null
        );
    }


    public static EvidenceFileEntity toEntity(EvidenceFile file, EvidenceEntity evidenceEntity){

        EvidenceFileEntity entity = new EvidenceFileEntity();

        entity.setFileID(file.getFileID());
        entity.setFileName(file.getFileName());
        entity.setFileType(file.getFileType());
        entity.setFileData(file.getFileData());


        entity.setEvidenceEntity(evidenceEntity);

        return entity;
    }
}
