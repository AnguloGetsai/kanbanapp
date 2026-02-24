package com.utez.kanban.kanban.domain.model.exeption.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
