package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepositoryPort {
    User saveUser(User user);
    Optional<User> findById(Long id);
    boolean findByEmail(String email);
    void safeCode(String code, String email,LocalDateTime time);
}
