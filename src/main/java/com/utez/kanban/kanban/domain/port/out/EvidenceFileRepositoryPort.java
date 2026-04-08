package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.EvidenceFile;

import java.util.List;

public interface EvidenceFileRepositoryPort {
    void saveAll(List<EvidenceFile> files);
}
