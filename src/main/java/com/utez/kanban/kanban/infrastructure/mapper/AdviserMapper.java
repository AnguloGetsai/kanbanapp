package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.infrastructure.entity.AdviserEntity;

public class AdviserMapper {


    public static Adviser toAdviser(AdviserEntity adviserEntity) {
        return new Adviser(
                adviserEntity.getAdviserID(),
                adviserEntity.getFirstName(),
                adviserEntity.getLastName(),
                adviserEntity.getImage(),
                UserMapper.toUser(adviserEntity.getUserEntity()),
                AdminMapper.toAdmin(adviserEntity.getAdminEntity())

        );
    }

    public static AdviserEntity toAdviserEntity(Adviser adviser){
        return new AdviserEntity(
                adviser.getAdviserID(),
                adviser.getFirstName(),
                adviser.getLastName(),
                adviser.getImage(),
                UserMapper.toUserEntity(adviser.getUser()),
                AdminMapper.toAdminEntity(adviser.getAdmin())
        );
    }
}
