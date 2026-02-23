package com.utez.kanban.kanban.infrastructure.mail;


import com.utez.kanban.kanban.domain.port.out.EmailRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;
import com.utez.kanban.kanban.infrastructure.error.ErrorResponse;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class EmailAdapter implements EmailRepositoryPort {
    private final JavaMailSender javaMailSender;
    private final UserRepositoryPort userRepositoryPort;


    public EmailAdapter(JavaMailSender javaMailSender,
                        UserRepositoryPort userRepositoryPort){
        this.javaMailSender = javaMailSender;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public void sendVerificationCode(String email, String code) {
        LocalDateTime time;

        if(userRepositoryPort.findByEmail(email)){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Verification Code");
            message.setText("This is your code of verification: " + code );

            javaMailSender.send(message);
        }

    }


}
