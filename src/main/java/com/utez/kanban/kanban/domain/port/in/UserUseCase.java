package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.User;


import java.util.Optional;

public interface UserUseCase {
    User createUser(User user);
    Optional<User> findById(Long id);
    void registerEmail(String email);
    void login(String email, String password);
    void validateVerificationCode(String email, String code);
    void changePassword(String email);
    void addPassword(String email, String password);

}
