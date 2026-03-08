package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Board;
import com.utez.kanban.kanban.infrastructure.entity.BoardEntity;
import org.hibernate.engine.internal.StatisticalLoggingSessionEventListener;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

public class BoardMapper {
    public static Board toBoard(BoardEntity boardEntity){
        return new Board(
                boardEntity.getBoardID(),
                boardEntity.getSubject(),
                AdviserMapper.toAdviser(boardEntity.getAdviserEntity())
        );
    }

    public static BoardEntity toBoardEntity(Board board){
        return new BoardEntity(
                board.getBoardID(),
                board.getSubject(),
                AdviserMapper.toAdviserEntity(board.getAdviser())
        );
    }
}
