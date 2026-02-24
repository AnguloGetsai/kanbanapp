package com.utez.kanban.kanban.domain.model.exeption.user;

import javax.print.DocFlavor;

public class EmailNotVerifiedException extends RuntimeException{
    public EmailNotVerifiedException(String message){
         super(message);
    }
}
