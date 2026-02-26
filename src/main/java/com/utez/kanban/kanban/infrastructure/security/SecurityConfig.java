package com.utez.kanban.kanban.infrastructure.security;

import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChan(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())

                //configuracion de permisos
                .authorizeHttpRequests(auth -> auth
                        //endpoints publicos
                        .requestMatchers(
                                "/api/user/sendCode",
                                "/api/user/validateCode"
                        ).permitAll()

                        // todo lo demas requiere autenticaion
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form.disable())

                .build();

    }

}
