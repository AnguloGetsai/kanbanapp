package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.User;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> saveUser(User user);
    Optional<User> findById(Long id);
    boolean saveVerificationCode(String code, String email,LocalDateTime time);
    boolean authorizeVerification(String email, boolean state);
    boolean addPassword(String email, String password);
    Optional<User> findUserEmail(String email);
    boolean changeStatus(String email, boolean status);

}
