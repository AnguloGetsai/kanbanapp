package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.model.exeption.user.EmailAlreadyExistsException;
import com.utez.kanban.kanban.domain.model.exeption.user.EmailNotVerifiedException;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.domain.port.in.UserUseCase;
import com.utez.kanban.kanban.domain.port.out.EmailSenderPort;
import com.utez.kanban.kanban.domain.port.out.EncryptPasswordPort;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;



import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


public class UserUseCaseImp implements UserUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final EmailSenderPort emailSenderPort;
    private final EncryptPasswordPort encryptPasswordPort;

    public UserUseCaseImp(UserRepositoryPort userRepositoryPort,
                          EmailSenderPort emailSenderPort,
                          EncryptPasswordPort encryptPasswordPort){
        this.userRepositoryPort = userRepositoryPort;
        this.emailSenderPort = emailSenderPort;
        this.encryptPasswordPort = encryptPasswordPort;
    }


    @Override
    public Optional<User> findById(Long id) {
        return userRepositoryPort.findById(id);
    }

    @Override
    public void registerEmail(String email) {
        User user = userRepositoryPort.findUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        String subject = "Code of verification";
        String text = "This is your code of verification: ";
        if(user == null){
            throw new UserNotFoundException("User not found");
        }else if(user.isVerified()){
            throw new EmailAlreadyExistsException("Email address has already been verified");
        }else{
            String code = User.generateCode();
            emailSenderPort.send(email, subject, text + code);
            userRepositoryPort.saveVerificationCode(code, email , LocalDateTime.now().plusMinutes(3));
        }
    }

    @Override
    public void login(String email, String password) {
        User user = userRepositoryPort.findUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if(!userIsNull(user)){

            if(user.validateLogin(email, password)){
                // metodos para el caso del login exitoso
                System.out.println("Login exitoso");
            }
            throw new BusinessRuleViolationException("Invalid data");
        }


    }

    @Override
    public String validateVerificationCode(String email, String code) {


        User user = userRepositoryPort.findUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

            if(user.validateVerificationCode(code)){
                String token = UUID.randomUUID().toString();
                if(userRepositoryPort.authorizeVerification(email, true, token, LocalDateTime.now().plusMinutes(10))){
                    return token;
                }

            }
        throw new BusinessRuleViolationException("You have an error to add the verification");


    }

    @Override
    public void changePassword(String email) {
        String subject = "Code to change your password";
        String text = "This is your code: ";
        String code = User.generateCode();
        User user = userRepositoryPort.findUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if(!userIsNull(user) && user.isVerified()){
            emailSenderPort.send(email,subject, text + code );
            userRepositoryPort.saveVerificationCode(code, email, LocalDateTime.now().plusMinutes(3));
        }
    }

    @Override
    public void addPassword(String email, String password, String passwordToken) {


        User user = userRepositoryPort.findUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(user.validatePasswordToken(passwordToken) && user.isVerified()){

            String passEncrypt = encryptPasswordPort.encrypt(password);
            // change the verified code status to false
            userRepositoryPort.authorizeVerification(email, false, null, null);
            if(!userRepositoryPort.addPassword(email, passEncrypt)){
                throw new BusinessRuleViolationException("Invalid password");
            }
            return;


        }

        throw new BusinessRuleViolationException("Invalid credentials");
    }



    @Override
    public void enableUser(String email) {
        if(!userRepositoryPort.changeStatus(email, true)){
            throw new BusinessRuleViolationException("An error occurred while changing the state on true");
        }
    }

    @Override
    public void disableUser(String email) {
        if(!userRepositoryPort.changeStatus(email, false)){
            throw new BusinessRuleViolationException("An error occurred while changing the state on false");
        }
    }


    public boolean userIsNull(User user){
        if(user == null){
            throw new UserNotFoundException("User invalid");
        }
        return false;
    }
}
