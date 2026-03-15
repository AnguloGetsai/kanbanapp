package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Board;

import java.util.List;

public interface BoardRepositoryPort {
    void createBard(Board board);
    List<Board> getAllBoards();
}
