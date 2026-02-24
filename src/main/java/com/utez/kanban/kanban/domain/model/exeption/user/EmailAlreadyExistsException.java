package com.utez.kanban.kanban.domain.model.exeption.user;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String message){
        super(message);
    }
}
