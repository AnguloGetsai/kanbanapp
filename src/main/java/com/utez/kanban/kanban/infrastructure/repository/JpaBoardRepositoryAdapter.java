package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Board;
import com.utez.kanban.kanban.domain.port.out.BoardRepositoryPort;
import com.utez.kanban.kanban.infrastructure.mapper.BoardMapper;
import org.springframework.stereotype.Component;

@Component
public class JpaBoardRepositoryAdapter implements BoardRepositoryPort {
    private final JpaBoardRepository jpaBoardRepository;

    public JpaBoardRepositoryAdapter(JpaBoardRepository jpaBoardRepository){
        this.jpaBoardRepository = jpaBoardRepository;
    }

    @Override
    public void createBard(Board board) {
        jpaBoardRepository.save(BoardMapper.toBoardEntity(board));
    }
}
