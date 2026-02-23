package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.User;

import java.util.Optional;

public interface UserUseCase {
    User createUser(User user);
    Optional<User> findById(Long id);
}
