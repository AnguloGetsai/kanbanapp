package com.utez.kanban.kanban.application.service;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.port.in.AdminUseCase;

import java.util.List;

public class AdminService implements AdminUseCase {

    private final AdminUseCase adminUseCase;

    public AdminService(AdminUseCase adminUseCase){
        this.adminUseCase = adminUseCase;
    }

    @Override
    public List<Adviser> getAllAdvisers() {
        return  adminUseCase.getAllAdvisers();
    }

    @Override
    public void registerAdviserUser(Adviser adviser) {
        adminUseCase.registerAdviserUser(adviser);
    }
}
