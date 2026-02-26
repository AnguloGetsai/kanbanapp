package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepositoryPort {
    User saveUser(User user);
    Optional<User> findById(Long id);
    User findByEmail(String email);
    boolean saveVerificationCode(String code, String email,LocalDateTime time);
    boolean authorizeVerification(String email);

}
