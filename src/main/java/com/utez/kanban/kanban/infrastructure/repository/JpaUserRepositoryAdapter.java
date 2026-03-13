package com.utez.kanban.kanban.infrastructure.repository;


import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.UserEntity;
import com.utez.kanban.kanban.infrastructure.mapper.UserMapper;
import org.hibernate.engine.internal.NaturalIdLogging_$logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;


@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {
    private JpaUserRepository jpaUserRepository;



    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository ){
        this.jpaUserRepository = jpaUserRepository;

    }

    @Override
    public User saveUser(User user) {
        UserEntity created = jpaUserRepository.save(UserMapper.toUserEntity(user));
        return UserMapper.toUser(created);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public boolean saveVerificationCode(String code,String email, LocalDateTime time) {
        int rows = jpaUserRepository.saveCode(email, code,  time);
        System.out.println("No se cambioaron los datos");
        System.out.println("DATOS DEL ALUMNO "+email+ " " +code + " " + time);
        return (rows > 0);
    }

    @Override
    public boolean authorizeVerification(String email, boolean state, String passwordToken, LocalDateTime passwordTokenExpiration) {
        long id = jpaUserRepository.authorizeVerification(email,state, passwordToken, passwordTokenExpiration );
        return (id > 0);
    }

    @Override
    public boolean addPassword(String email, String password) {
        long id = jpaUserRepository.addPassword(email, password);
        return id > 0;
    }

    @Override
    public Optional<User> findUserEmail(String email) {
        return jpaUserRepository
                .findByEmail(email)
                .map(UserMapper::toUser);
    }

    @Override
    public boolean changeStatus(String email, boolean status) {
        return jpaUserRepository.changeStatus(email, status) == 1;
    }





}
