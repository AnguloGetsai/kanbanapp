package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.UserService;
import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.infrastructure.controller.DTO.LoginRequestDTO;
import com.utez.kanban.kanban.infrastructure.controller.DTO.SuccessResponse;
import com.utez.kanban.kanban.infrastructure.controller.DTO.UserCredentialDTO;
import com.utez.kanban.kanban.infrastructure.controller.DTO.UserPasswordDTO;
import com.utez.kanban.kanban.infrastructure.security.JwtService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@Validated
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
    public ResponseEntity<?> sendCode(@RequestParam  @Email String email){
        userService.registerEmail(email);
        return ResponseEntity.ok(Map.of("status",201,
                                        "message","Code sent"));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  loginDTO.getEmail(),
                  loginDTO.getPassword()
          )
        );

        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok(Map.of("token", token,
                                        "user", Map.of(
                                                    "email",user.getEmail(),
                                                    "rol",user.getRol()
                )));
    }


    @PostMapping("/validateCode")
    public ResponseEntity<?> validateCode(@Valid @RequestBody UserCredentialDTO userDTO){
        String PasswordToken = userService.validateVerificationCode(userDTO.getEmail(), userDTO.getCode());
        return ResponseEntity.ok(Map.of("status",201,
                                       "passwordToken",PasswordToken));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam @Email String email){
        userService.changePassword(email);
        return ResponseEntity.ok(new SuccessResponse(
                201,
                "Code sent"
        ));
    }


    @PostMapping("/addPassword")
    public ResponseEntity<?> addPassword(@Valid @RequestBody UserPasswordDTO userPassword){
        userService.addPassword(userPassword.getEmail(), userPassword.getPassword(), userPassword.getPasswordToken());
        return ResponseEntity.ok(new SuccessResponse(
                203,
                "Password added correctly"
        ));
    }

    @PostMapping("/disableUser")
    public ResponseEntity<?> disableUser(
            @RequestParam @Email String email
    ){
        userService.disableUser(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new SuccessResponse(
           203,
           "User was disable"
        ));
    }

    @PostMapping("/enableUser")
    public ResponseEntity<?> enableUser(
            @RequestParam @Email String email
    ){
        userService.enableUser(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new SuccessResponse(
                203,
                "User was enable"
        ));
    }

}
