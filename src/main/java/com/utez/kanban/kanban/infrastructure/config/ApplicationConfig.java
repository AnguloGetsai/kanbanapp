package com.utez.kanban.kanban.infrastructure.config;

import com.utez.kanban.kanban.application.service.StudentService;
import com.utez.kanban.kanban.application.usecase.StudentUseCaseImp;
import com.utez.kanban.kanban.domain.port.out.StudentRepositoryPort;
import com.utez.kanban.kanban.infrastructure.repository.JpaStudentRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
