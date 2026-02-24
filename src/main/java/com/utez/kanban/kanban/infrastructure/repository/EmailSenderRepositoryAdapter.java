package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.port.out.EmailSenderPort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderRepositoryAdapter implements EmailSenderPort {

    private final JavaMailSender javaMailSender;

    public EmailSenderRepositoryAdapter(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(String emailTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailTo);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
