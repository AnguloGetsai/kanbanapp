package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/api/user/sendCode")
    public ResponseEntity<?> sendCode(@RequestParam String email){
        userService.registerEmail(email);
        return ResponseEntity.ok("Registro exitoso");
    }
}
