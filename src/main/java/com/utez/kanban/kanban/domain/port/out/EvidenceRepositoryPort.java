package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Evidence;
import com.utez.kanban.kanban.domain.model.StudentTask;

import java.util.List;

public interface EvidenceRepositoryPort {
    Evidence save(Evidence evidence);
    List<Evidence> findByStudentTask(StudentTask studentTask);
}
