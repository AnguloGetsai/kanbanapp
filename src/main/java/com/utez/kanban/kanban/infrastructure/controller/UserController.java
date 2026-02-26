package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.UserService;
import com.utez.kanban.kanban.infrastructure.controller.dto.LoginRequestDTO;
import com.utez.kanban.kanban.infrastructure.controller.dto.UserCredentialDTO;
import jakarta.validation.Valid;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToUrl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/sendCode")
    public ResponseEntity<?> sendCode(@RequestParam String email){
        userService.registerEmail(email);
        System.out.println("Entro al metodo send code");
        return ResponseEntity.ok("Registro exitoso");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginDTO){
        userService.login(loginDTO.getEmail(), loginDTO.getPassword());
        return ResponseEntity.ok("Successful login");
    }


    @PostMapping("/validateCode")
    public ResponseEntity<?> validateCode(@Valid @RequestBody UserCredentialDTO userDTO){
        userService.validateVerificationCode(userDTO.getEmail(), userDTO.getCode());
        return ResponseEntity.ok("The code was validated");
    }
}
