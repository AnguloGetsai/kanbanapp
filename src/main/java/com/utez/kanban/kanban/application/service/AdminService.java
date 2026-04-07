package com.utez.kanban.kanban.application.service;



import com.utez.kanban.kanban.domain.model.Admin;
import com.utez.kanban.kanban.domain.model.Adviser;

import com.utez.kanban.kanban.domain.model.Board;
import com.utez.kanban.kanban.domain.model.Task;
import com.utez.kanban.kanban.domain.port.in.AdminUseCase;

import java.util.List;
import java.util.Optional;

// nuevo espacio vacio xd

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

    @Override
    public Optional<Admin> getAdminInformation(String email) {
        return adminUseCase.getAdminInformation(email);
    }

    @Override
    public void uploadLogo(String email, byte[] image) {
        adminUseCase.uploadLogo(email, image);
    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminUseCase.findByEmail(email);
    }

    @Override
    public List<Board> getAllBoards() {
        return adminUseCase.getAllBoards();
    }

    @Override
    public void updateAdminInformation(String email, Admin admin) {
        adminUseCase.updateAdminInformation(email, admin);
    }

    @Override
    public List<Task> getTasksByAdviser(Long adviserId, String adminEmail) {
        return adminUseCase.getTasksByAdviser(adviserId, adminEmail);
    }


}
