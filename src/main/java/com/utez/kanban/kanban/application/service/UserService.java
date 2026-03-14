package com.utez.kanban.kanban.application.service;

import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.port.in.UserUseCase;
import org.springframework.security.core.parameters.P;


import java.util.Optional;

public class UserService implements UserUseCase {


    private final  UserUseCase userUseCase;
    public UserService(UserUseCase userUseCase){
        this.userUseCase = userUseCase;
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
    public String validateVerificationCode(String email, String code) {
        return userUseCase.validateVerificationCode(email, code);
    }

    @Override
    public void changePassword(String email) {
        userUseCase.changePassword(email);
    }

    @Override
    public void addPassword(String email, String password, String passwordToken) {
        userUseCase.addPassword(email, password, passwordToken);
    }

    @Override
    public void enableUser(String email ) {
        userUseCase.enableUser(email);
    }

    @Override
    public void disableUser(String email ) {
        userUseCase.disableUser(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userUseCase.findByEmail(email);
    }


}
