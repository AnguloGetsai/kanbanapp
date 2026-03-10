package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Admin;
import com.utez.kanban.kanban.infrastructure.entity.AdminEntity;

public class AdminMapper {
    public static Admin toAdmin(AdminEntity adminEntity){
        return new Admin(
                adminEntity.getAdminID(),
                adminEntity.getFirstName(),
                adminEntity.getLastName(),
                adminEntity.getImage(),
                UserMapper.toUser(adminEntity.getUserEntity())
        );
    }

    public static AdminEntity toAdminEntity(Admin admin){
        return new AdminEntity(
                admin.getAdminID(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getImage(),
                UserMapper.toUserEntity(admin.getUser())
        );
    }
}
