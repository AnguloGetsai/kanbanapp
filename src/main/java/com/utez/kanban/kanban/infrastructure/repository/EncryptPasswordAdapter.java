package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.port.out.EncryptPasswordPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptPasswordAdapter implements EncryptPasswordPort {
    private final PasswordEncoder passwordEncoder;

    public EncryptPasswordAdapter(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }
}
