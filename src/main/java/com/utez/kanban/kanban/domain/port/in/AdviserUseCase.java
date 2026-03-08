package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.Adviser;

public interface AdviserUseCase {
    void registerAdviser(Adviser adviser);
}
