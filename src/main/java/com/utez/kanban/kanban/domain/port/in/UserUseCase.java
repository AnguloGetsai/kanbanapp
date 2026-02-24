package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserUseCase {
    User createUser(User user);
    Optional<User> findById(Long id);
    void registerEmail(String email);
    void login(String email, String password);

}
