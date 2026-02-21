package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.port.in.UserUseCase;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;

public class UserUseCaseImp implements UserUseCase {
    private final UserRepositoryPort userRepositoryPort;

    public UserUseCaseImp(UserRepositoryPort userRepositoryPort){
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createUser(User user) {
        return userRepositoryPort.saveUser(user);
    }
}
