package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.Board;

import java.util.Base64;

public class BoardCard {
    private Long boardId;
    private String firstName;
    private String lastName;
    private String logo;
    private Long adviserId;



    public static BoardCard toBoardCard(Board board){

        String logoBase64;
        if(board.getAdviser().getImage() == null){
             logoBase64 = "SIN LOGO";
        }else{
            logoBase64 = Base64.getEncoder().encodeToString(board.getAdviser().getImage());
        }
        return new BoardCard(
                board.getBoardID(),
                board.getAdviser().getFirstName(),
                board.getAdviser().getLastName(),
                logoBase64,
                board.getAdviser().getAdviserID()
        );
    }

    public BoardCard(Long boardId, String firstName, String lastName, String logo, Long adviserId) {
        this.boardId = boardId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.logo = logo;
        this.adviserId = adviserId;

    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(Long adviserId) {
        this.adviserId = adviserId;
    }
}
