package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.UserService;
import com.utez.kanban.kanban.infrastructure.controller.userDTO.LoginRequestDTO;
import com.utez.kanban.kanban.infrastructure.controller.userDTO.UserCredentialDTO;
import com.utez.kanban.kanban.infrastructure.controller.userDTO.UserPasswordDTO;
import jakarta.validation.Valid;
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
        return ResponseEntity.ok("Successful registration");
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

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String email){
        userService.changePassword(email);
        return ResponseEntity.ok("A code to verify that it is you was sent");
    }


    @PostMapping("/addPassword")
    public ResponseEntity<?> addPassword(@Valid @RequestBody UserPasswordDTO userPassword){
        userService.addPassword(userPassword.getEmail(), userPassword.getPassword());
        return ResponseEntity.ok("Password changed correctly");
    }


}
