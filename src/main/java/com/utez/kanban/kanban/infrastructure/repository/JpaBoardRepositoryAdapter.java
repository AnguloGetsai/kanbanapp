package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.Board;
import com.utez.kanban.kanban.domain.port.out.BoardRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.BoardEntity;
import com.utez.kanban.kanban.infrastructure.mapper.BoardMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Board> getAllBoards() {
        List<Board> boardList = new ArrayList<>();
        for(BoardEntity boardEntity: jpaBoardRepository.findAll()){
            boardList.add(BoardMapper.toBoard(boardEntity));
        }
        return boardList;
    }

    @Override
    public Optional<Board> findBoardByAdviserId(Long id) {
        return jpaBoardRepository.findBoardByAdviserID(id)
                .map(BoardMapper::toBoard);
    }
}
