package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Evidence;

public interface EvidenceRepositoryPort {
    Evidence save(Evidence evidence);
}
