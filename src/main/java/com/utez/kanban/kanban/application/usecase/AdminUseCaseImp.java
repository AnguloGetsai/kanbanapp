package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.port.in.AdminUseCase;
import com.utez.kanban.kanban.domain.port.out.AdviserRepositoryPort;

import java.util.List;

public class AdminUseCaseImp implements AdminUseCase {

    //falta hacer la inyeci[on en config
    private final AdviserRepositoryPort adviserRepositoryPort;

    public AdminUseCaseImp(AdviserRepositoryPort adviserRepositoryPort){
        this.adviserRepositoryPort = adviserRepositoryPort;
    }


    @Override
    public void registerAdviser(Adviser adviser) {
        adviserRepositoryPort.
    }

    @Override
    public void disableAdviser(Long id) {

    }

    @Override
    public List<Adviser> getAllAdvisers() {
        return List.of();
    }
}
