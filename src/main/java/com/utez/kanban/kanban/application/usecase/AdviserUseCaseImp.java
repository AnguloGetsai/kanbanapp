package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.port.in.AdviserUseCase;
import com.utez.kanban.kanban.domain.port.out.AdviserRepositoryPort;

public class AdviserUseCaseImp implements AdviserUseCase {
    private final AdviserRepositoryPort adviserRepositoryPort;

    public AdviserUseCaseImp(AdviserRepositoryPort adviserRepositoryPort){
        this.adviserRepositoryPort = adviserRepositoryPort;
    }
    @Override
    public void registerAdviser(Adviser adviser) {
        adviserRepositoryPort.saveAdviser(adviser);
    }
}
