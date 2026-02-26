package com.utez.kanban.kanban.infrastructure.repository;


import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.UserEntity;
import com.utez.kanban.kanban.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;


@Component
public class JpaUserRepositoryAdapter implements UserRepositoryPort {
    private JpaUserRepository jpaUserRepository;

    public JpaUserRepositoryAdapter(JpaUserRepository jpaUserRepository){
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = UserMapper.toUserEntity(user);
        userEntity = jpaUserRepository.save(userEntity);
        return UserMapper.toUser(userEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User findByEmail(String email) {
        return UserMapper.toUser(jpaUserRepository.findByEmail(email));
    }

    @Override
    public boolean saveVerificationCode(String code,String email, LocalDateTime time) {
        int rows = jpaUserRepository.saveCode(email, code,  time);
        System.out.println("No se cambioaron los datos");
        System.out.println("DATOS DEL ALUMNO "+email+ " " +code + " " + time);
        return (rows > 0);
    }

    @Override
    public boolean authorizeVerification(String email) {
        long id = jpaUserRepository.authorizeVerification(email);
        return (id > 0);
    }


}
