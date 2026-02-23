package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.EmailService;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class UserController {
    private final EmailService emailService;
    private final UserRepositoryPort userRepositoryPort;

    public UserController(EmailService emailService,
                          UserRepositoryPort userRepositoryPort){
        this.emailService = emailService;
        this.userRepositoryPort = userRepositoryPort;
    }
    @PostMapping("/api/user/sendCode")
    public ResponseEntity<?> sendCode(@RequestParam String email){
        String code = emailService.generateCode();
        userRepositoryPort.safeCode(email, code, LocalDateTime.now());
        emailService.sendVerificationCode(email, code);
        return ResponseEntity.ok("correo enviado");
    }
}
