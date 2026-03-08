package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Board;

public interface BoardRepositoryPort {
    void createBard(Board board);
}
