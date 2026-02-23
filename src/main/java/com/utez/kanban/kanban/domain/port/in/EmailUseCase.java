package com.utez.kanban.kanban.domain.port.in;

public interface EmailUseCase {
    void sendVerificationCode(String email, String code);
}
