package com.utez.kanban.kanban.application.service;

import com.utez.kanban.kanban.domain.port.in.EmailUseCase;
import com.utez.kanban.kanban.domain.port.out.EmailRepositoryPort;

import java.util.Random;

public class EmailService implements EmailUseCase {

    private final EmailUseCase emailUseCase;

    public EmailService(EmailUseCase emailUseCase){
        this.emailUseCase  = emailUseCase;
    }


    public String generateCode(){
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    @Override
    public void sendVerificationCode(String email, String code) {
        emailUseCase.sendVerificationCode(email,code);
    }
}
