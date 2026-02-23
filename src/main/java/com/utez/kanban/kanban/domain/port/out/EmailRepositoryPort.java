package com.utez.kanban.kanban.domain.port.out;

import java.time.LocalDateTime;

public interface EmailRepositoryPort {
    void sendVerificationCode(String email, String code);

}
