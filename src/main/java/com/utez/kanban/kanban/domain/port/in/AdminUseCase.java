package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.Adviser;

import java.util.List;
import java.util.Optional;

public interface AdminUseCase {
    void registerAdviser(Adviser adviser);
    void disableAdviser(Long id);
    List<Adviser> getAllAdvisers();
}
