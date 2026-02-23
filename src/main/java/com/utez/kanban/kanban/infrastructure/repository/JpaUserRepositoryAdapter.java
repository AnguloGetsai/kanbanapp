package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.KanbanApplication;
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
        return null;
    }

    @Override
    public boolean findByEmail(String email) {
        return jpaUserRepository.findByEmailAndRol(email) > 0;

    }

    @Override
    public void safeCode(String code,String email, LocalDateTime time) {
        int rows = jpaUserRepository.saveCode(email, code,  time);
        System.out.println("No se cambioaron los datos");
    }
}
