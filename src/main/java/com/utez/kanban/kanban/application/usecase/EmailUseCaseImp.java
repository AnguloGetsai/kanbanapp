package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.port.in.EmailUseCase;
import com.utez.kanban.kanban.domain.port.out.EmailRepositoryPort;

import java.util.Random;


public class EmailUseCaseImp implements EmailUseCase {


    private final EmailRepositoryPort emailRepositoryPort;

    public EmailUseCaseImp(EmailRepositoryPort emailRepositoryPort){

        this.emailRepositoryPort = emailRepositoryPort;
    }



    @Override
    public void sendVerificationCode(String email, String code) {
        emailRepositoryPort.sendVerificationCode(email, code);
    }

}
