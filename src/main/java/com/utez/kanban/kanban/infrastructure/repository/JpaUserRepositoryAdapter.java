package com.utez.kanban.kanban.infrastructure.repository;

import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;
import com.utez.kanban.kanban.infrastructure.entity.UserEntity;
import com.utez.kanban.kanban.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Component;


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
}
