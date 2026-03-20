package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepositoryPort {
    void createBard(Board board);
    List<Board> getAllBoards();
    Optional<Board> findBoardByAdviserId(Long id);
}
