package com.utez.kanban.kanban.application.service;

import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.port.in.UserUseCase;

public class UserService implements UserUseCase {

    private UserUseCase userUseCase;
    public UserService(UserUseCase userUseCase){
        this.userUseCase = userUseCase;
    }
    @Override
    public User createUser(User user) {
       return  userUseCase.createUser(user);
    }
}
