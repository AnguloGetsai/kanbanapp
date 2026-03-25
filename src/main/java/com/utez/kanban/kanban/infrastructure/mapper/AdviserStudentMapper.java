package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.AdviserStudent;
import com.utez.kanban.kanban.infrastructure.entity.AdviserStudentEntity;
import com.utez.kanban.kanban.infrastructure.entity.AdviserStudentID;
import org.springframework.security.web.server.util.matcher.NegatedServerWebExchangeMatcher;

public class AdviserStudentMapper {
    public static AdviserStudent toAdviserStudent(AdviserStudentEntity adviserStudentEntity){
        return new AdviserStudent(
                AdviserMapper.toAdviser(adviserStudentEntity.getAdviserEntity()),
                StudentMapper.toStudent(adviserStudentEntity.getStudentEntity()),
                adviserStudentEntity.isStatus()
        );
    }



    public static AdviserStudentEntity toAdviserStudentEntity(AdviserStudent adviserStudent){
        return new AdviserStudentEntity(
                new AdviserStudentID(adviserStudent.getAdviser().getAdviserID(), adviserStudent.getStudent().getStudentID()),
                AdviserMapper.toAdviserEntity(adviserStudent.getAdviser()),
                StudentMapper.toStudentEntity(adviserStudent.getStudent()),
                adviserStudent.isStatus()
        );
    }
}
