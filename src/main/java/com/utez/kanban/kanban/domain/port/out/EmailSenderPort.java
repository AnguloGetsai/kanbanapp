package com.utez.kanban.kanban.domain.port.out;

public interface EmailSenderPort {
    void send(String emailTo, String subject, String text);
}
