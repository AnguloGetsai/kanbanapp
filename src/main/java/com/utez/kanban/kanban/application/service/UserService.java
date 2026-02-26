package com.utez.kanban.kanban.application.service;

import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.port.in.UserUseCase;


import java.util.Optional;

public class UserService implements UserUseCase {


    private final  UserUseCase userUseCase;
    public UserService(UserUseCase userUseCase){
        this.userUseCase = userUseCase;
    }
    @Override
    public User createUser(User user) {
       return  userUseCase.createUser(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userUseCase.findById(id);
    }

    @Override
    public void registerEmail(String email) {
        userUseCase.registerEmail(email);
    }

    @Override
    public void login(String email, String password) {
        userUseCase.login(email, password);
    }

    @Override
    public void validateVerificationCode(String email, String code) {
        userUseCase.validateVerificationCode(email, code);
    }


}
