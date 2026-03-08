package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Rol;
import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.port.in.AdminUseCase;
import com.utez.kanban.kanban.domain.port.out.AdviserRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;
import org.aspectj.weaver.NewConstructorTypeMunger;

import java.util.List;

public class AdminUseCaseImp implements AdminUseCase {

    private final AdviserRepositoryPort adviserRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    public AdminUseCaseImp(AdviserRepositoryPort adviserRepositoryPort,
                           UserRepositoryPort userRepositoryPort){
        this.adviserRepositoryPort = adviserRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }




    @Override
    public List<Adviser> getAllAdvisers() {
        return adviserRepositoryPort.getAllAdvisers();
    }

    @Override
    public void registerAdviserUser(User user) {
        user.setRol(Rol.ADVISER.name());
        userRepositoryPort.saveUser(user);
    }



}
