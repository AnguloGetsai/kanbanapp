package com.utez.kanban.kanban.infrastructure.config;

import com.utez.kanban.kanban.application.service.AdminService;
import com.utez.kanban.kanban.application.service.AdviserService;
import com.utez.kanban.kanban.application.service.UserService;
import com.utez.kanban.kanban.application.usecase.*;
import com.utez.kanban.kanban.domain.port.in.StudentUseCase;
import com.utez.kanban.kanban.domain.port.out.*;
import com.utez.kanban.kanban.infrastructure.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

    // ✅ CORREGIDO
    @Bean
    public StudentUseCase studentUseCase(
            TaskRepositoryPort taskRepositoryPort,
            StudentRepositoryPort studentRepositoryPort,
            NotificationRepositoryPort notificationRepositoryPort,
            AdviserRepositoryPort adviserRepositoryPort,
            StudentTaskEntityRepositoryPort studentTaskEntityRepositoryPort,
            BoardRepositoryPort boardRepositoryPort,
            AdviserStudentRepository adviserStudentRepository,
            EvidenceRepositoryPort evidenceRepositoryPort,
            EvidenceFileRepositoryPort evidenceFileRepositoryPort
    ) {
        return new StudentUseCaseImpl(
                taskRepositoryPort,
                studentRepositoryPort,
                notificationRepositoryPort,
                adviserRepositoryPort,
                studentTaskEntityRepositoryPort,
                boardRepositoryPort,
                adviserStudentRepository,
                evidenceRepositoryPort,
                evidenceFileRepositoryPort
        );
    }

    @Bean
    @Primary
    public StudentRepositoryPort studentRepositoryPort(JpaStudentRepositoryAdapter adapter) {
        return adapter;
    }

    @Bean
    @Primary
    public TaskRepositoryPort taskRepositoryPort(JpaTaskRepositoryAdapter adapter) {
        return adapter;
    }

    @Bean
    @Primary
    public NotificationRepositoryPort notificationRepositoryPort(
            JpaNotificationRepositoryAdapter adapter
    ) {
        return adapter;
    }

    @Bean
    @Primary
    public AdviserRepositoryPort adviserRepositoryPort(JpaAdviserRepositoryAdapter adapter) {
        return adapter;
    }

    // 🔽 ESTE SÍ SE QUEDA (pero NO se usa en StudentUseCase)
    @Bean
    @Primary
    public AttachmentRepositoryPort attachmentRepositoryPort(
            JpaAttachmentRepositoryAdapter adapter
    ) {
        return adapter;
    }

    // ================= USER =================

    @Bean
    public UserService userService(
            UserRepositoryPort userRepositoryPort,
            EmailSenderPort emailSenderPort,
            EncryptPasswordPort encryptPasswordPort
    ) {
        return new UserService(
                new UserUseCaseImp(userRepositoryPort, emailSenderPort, encryptPasswordPort)
        );
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(JpaUserRepositoryAdapter adapter) {
        return adapter;
    }

    // ================= ADMIN =================

    @Bean
    public AdminService adminService(
            AdviserRepositoryPort adviserRepositoryPort,
            UserRepositoryPort userRepositoryPort,
            AdminRepositoryPort adminRepositoryPort,
            BoardRepositoryPort boardRepositoryPort
    ) {
        return new AdminService(
                new AdminUseCaseImp(adviserRepositoryPort, userRepositoryPort, adminRepositoryPort, boardRepositoryPort)
        );
    }

    @Bean
    public AdminRepositoryPort adminRepositoryPort(JpaAdminRepositoryAdapter adapter) {
        return adapter;
    }

    // ================= ADVISER =================

    @Bean
    public AdviserService adviserService(
            AdviserRepositoryPort adviserRepositoryPort,
            UserRepositoryPort userRepositoryPort,
            StudentRepositoryPort studentRepositoryPort,
            AdviserStudentRepository adviserStudentRepository,
            TaskRepositoryPort taskRepositoryPort,
            BoardRepositoryPort boardRepositoryPort,
            AttachmentRepositoryPort attachmentRepositoryPort,
            StudentTaskRepositoryPort studentTaskRepositoryPort
    ) {
        return new AdviserService(
                new AdviserUseCaseImp(
                        adviserRepositoryPort,
                        userRepositoryPort,
                        studentRepositoryPort,
                        adviserStudentRepository,
                        taskRepositoryPort,
                        boardRepositoryPort,
                        attachmentRepositoryPort,
                        studentTaskRepositoryPort
                )
        );
    }

    @Bean
    @Primary
    public BoardRepositoryPort boardRepositoryPort(JpaBoardRepositoryAdapter adapter) {
        return adapter;
    }

    @Bean
    @Primary
    public AdviserStudentRepository adviserStudentRepository(
            JpaAdviserStudentRepositoryAdapter adapter
    ) {
        return adapter;
    }
}