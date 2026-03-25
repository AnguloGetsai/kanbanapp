package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.AdviserStudent;
import com.utez.kanban.kanban.infrastructure.entity.AdviserStudentEntity;

public class AdviserStudentMapper {

    public static AdviserStudentEntity toAdviserStudentEntity(AdviserStudent model) {
        AdviserStudentEntity entity = new AdviserStudentEntity();

        entity.setAdviserId(model.getAdviserId());
        entity.setStudentId(model.getStudentId());
        entity.setStatus(model.isStatus());

        return entity;
    }

    public static AdviserStudent toModel(AdviserStudentEntity entity) {
        AdviserStudent model = new AdviserStudent();

        model.setAdviserId(entity.getAdviserId());
        model.setStudentId(entity.getStudentId());
        model.setStatus(entity.isStatus());

        return model;
    }
}