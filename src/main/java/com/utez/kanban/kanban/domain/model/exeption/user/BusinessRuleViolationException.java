package com.utez.kanban.kanban.domain.model.exeption.user;

public class BusinessRuleViolationException extends RuntimeException{
    public BusinessRuleViolationException(String message){
        super(message);
    }
}
