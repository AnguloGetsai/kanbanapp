package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.infrastructure.entity.UserEntity;


public class UserMapper {
    public static User toUser(UserEntity userEntity){
        return new User(
                userEntity.getUserID(),
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getRol(),
                userEntity.isStatus(),
                userEntity.getVerificationCode(),
                userEntity.isVerified(),
                userEntity.getExpirationTime()
        );
    }

    public static UserEntity toUserEntity(User user){
        return new UserEntity(
                user.getPassword(),
                user.getEmail(),
                user.getRol(),
                user.isStatus(),
                user.getVerificationCode(),
                user.isVerified(),
                user.getExpirationTime()
        );
    }
}
