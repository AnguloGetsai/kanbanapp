package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.UserService;
import com.utez.kanban.kanban.infrastructure.controller.userDTO.LoginRequestDTO;
import com.utez.kanban.kanban.infrastructure.controller.userDTO.UserCredentialDTO;
import com.utez.kanban.kanban.infrastructure.controller.userDTO.UserPasswordDTO;
import com.utez.kanban.kanban.infrastructure.security.JwtService;
import jakarta.validation.Valid;
import org.aspectj.runtime.internal.cflowstack.ThreadStackImpl11;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public UserController(
            UserService userService,
            AuthenticationManager  authenticationManager,
            JwtService jwtService

    ){
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/sendCode")
    public ResponseEntity<?> sendCode(@RequestParam String email){
        userService.registerEmail(email);
        return ResponseEntity.ok("Successful registration");
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  loginDTO.getEmail(),
                  loginDTO.getPassword()
          )
        );
        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok(Map.of("token", token));
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
