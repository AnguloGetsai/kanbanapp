package com.utez.kanban.kanban.infrastructure.config;

import com.utez.kanban.kanban.application.service.AdminService;
import com.utez.kanban.kanban.application.service.StudentService;
import com.utez.kanban.kanban.application.service.UserService;
import com.utez.kanban.kanban.application.usecase.AdminUseCaseImp;
import com.utez.kanban.kanban.application.usecase.AdviserUseCaseImp;
import com.utez.kanban.kanban.application.usecase.StudentUseCaseImp;
import com.utez.kanban.kanban.application.usecase.UserUseCaseImp;
import com.utez.kanban.kanban.domain.port.in.AdviserUseCase;
import com.utez.kanban.kanban.domain.port.out.*;
import com.utez.kanban.kanban.infrastructure.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class ApplicationConfig {

    @Bean
    public StudentService studentService(StudentRepositoryPort studentRepositoryPort){
        return new StudentService(
                new StudentUseCaseImp(studentRepositoryPort)
        );
    }

    @Bean
    public StudentRepositoryPort studentRepositoryPort(JpaStudentRepositoryAdapter jpaStudentRepositoryAdapter){
        return jpaStudentRepositoryAdapter;
    }

    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort, EmailSenderPort emailSenderPort, EncryptPasswordPort encryptPasswordPort){
        return new UserService(
                new UserUseCaseImp(userRepositoryPort, emailSenderPort, encryptPasswordPort)

        );
    }


    @Bean
    public UserRepositoryPort userRepositoryPort(JpaUserRepositoryAdapter jpaUserRepositoryAdapter){
        return jpaUserRepositoryAdapter;
    }

    @Bean
    public AdminService adminService(AdviserRepositoryPort adviserRepositoryPort, UserRepositoryPort userRepositoryPort, AdminRepositoryPort adminRepositoryPort, BoardRepositoryPort boardRepositoryPort){
        return new AdminService(
                new AdminUseCaseImp(adviserRepositoryPort, userRepositoryPort, adminRepositoryPort, boardRepositoryPort)
        );
    }

    @Bean
    public AdviserRepositoryPort adviserRepositoryPort(JpaAdviserRepositoryAdapter jpaAdviserRepositoryAdapter){
        return jpaAdviserRepositoryAdapter;
    }

    @Bean
    public AdminRepositoryPort adminRepositoryPort(JpaAdminRepositoryAdapter jpaAdminRepositoryAdapter){
        return jpaAdminRepositoryAdapter;
    }

    @Bean
    public BoardRepositoryPort boardRepositoryPort(JpaBoardRepositoryAdapter jpaBoardRepositoryAdapter){
        return jpaBoardRepositoryAdapter;
    }

}
