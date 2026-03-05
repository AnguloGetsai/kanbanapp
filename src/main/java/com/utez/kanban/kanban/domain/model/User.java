package com.utez.kanban.kanban.domain.model;


import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;

import java.time.LocalDateTime;
import java.util.Random;

public class User {

    private Long userID;
    private String password;
    private String email;
    private String rol;
    private boolean status;
    private String verificationCode;
    private boolean isVerified;
    private LocalDateTime expirationTime;

    public User() {
    }

    public User(Long userID, String password, String email, String rol,
                boolean status, String verificationCode, boolean isVerified,
                LocalDateTime expirationTime) {
        this.userID = userID;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.status = status;
        this.verificationCode = verificationCode;
        this.isVerified = isVerified;
        this.expirationTime = expirationTime;
    }

    //para la validacion del login
    public boolean validateLogin(String email, String password){
        if(this.email.equals(email) && this.password.equals(password)){
            return true;
        }
        return false;
    }
    // metodo para generar el codigo de verificacion
    public static String generateCode(){
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
    // metodo para validar el codigo de verificacion
    public boolean validateVerificationCode(String code){
        if(!LocalDateTime.now().isAfter(expirationTime)){
            if(this.verificationCode.equals(code)){
                return true;
            }
            throw new BusinessRuleViolationException("Invalid code");
        }
        throw new BusinessRuleViolationException("The verification code has expired");
    }
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
