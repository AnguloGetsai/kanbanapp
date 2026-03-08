package com.utez.kanban.kanban.domain.model;

public class Board {
    private Long boardID;
    private String subject;
    private Adviser adviser;

    public Board(Long boardID, String subject, Adviser adviser) {
        this.boardID = boardID;
        this.subject = subject;
        this.adviser = adviser;
    }


    public Board(){}

    public Long getBoardID() {
        return boardID;
    }

    public void setBoardID(Long boardID) {
        this.boardID = boardID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Adviser getAdviser() {
        return adviser;
    }

    public void setAdviser(Adviser adviser) {
        this.adviser = adviser;
    }
}
