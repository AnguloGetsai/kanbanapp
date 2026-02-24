package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.model.exeption.user.EmailAlreadyExistsException;
import com.utez.kanban.kanban.domain.model.exeption.user.EmailNotVerifiedException;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.domain.port.in.UserUseCase;
import com.utez.kanban.kanban.domain.port.out.EmailSenderPort;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToUrl;


import java.time.LocalDateTime;
import java.util.Optional;


public class UserUseCaseImp implements UserUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final EmailSenderPort emailSenderPort;

    public UserUseCaseImp(UserRepositoryPort userRepositoryPort,
                          EmailSenderPort emailSenderPort){
        this.userRepositoryPort = userRepositoryPort;
        this.emailSenderPort = emailSenderPort;
    }

    @Override
    public User createUser(User user) {
        return userRepositoryPort.saveUser(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepositoryPort.findById(id);
    }

    @Override
    public void registerEmail(String email) {
        User user = userRepositoryPort.findByEmail(email);
        if(user == null){
            throw new EmailNotVerifiedException("email invalido");
        }else if(user.isVerified()){
            throw new EmailAlreadyExistsException("This email address: " + email + "  has already been verified.");
        }else{
            String code = User.generateCode();
            emailSenderPort.send(email, "Code of verification", "This is your code of verification: "+code);
            userRepositoryPort.saveVerificationCode(code, email , LocalDateTime.now());
        }
    }

    @Override
    public void login(String email, String password) {
        User user = userRepositoryPort.findByEmail(email);
        if(user == null){
            throw new UserNotFoundException("Invalid User");
        }
        if(user.validateLogin(email, password)){
            // metodos para el caso del login exitoso
            System.out.println("Login exitoso");
        }

    }





}
