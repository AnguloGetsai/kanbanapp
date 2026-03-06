package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Admin;
import com.utez.kanban.kanban.infrastructure.entity.AdminEntity;

public class AdminMapper {
    public static Admin toAdmin(AdminEntity adminEntity){
        return new Admin();
    }

    public static AdminEntity toAdminEntity(Admin admin){
        return new AdminEntity();
    }
}
