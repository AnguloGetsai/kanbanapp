package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.StudentTask;
import com.utez.kanban.kanban.infrastructure.entity.StudentTaskEntity;
import com.utez.kanban.kanban.infrastructure.entity.StudentTaskID;


public class StudentTaskMapper {
    public static StudentTaskEntity toStudentTaskEntity(StudentTask studentTask){
        return new StudentTaskEntity(
                new StudentTaskID(studentTask.getStudent().getStudentID(), studentTask.getTask().getTaskID()),
                StudentMapper.toStudentEntity(studentTask.getStudent()),
                TaskMapper.toTaskEntity(studentTask.getTask()),
                studentTask.getStatus(),
                studentTask.getAssignedDate(),
                studentTask.getCulminationDate(),
                studentTask.getGrade(),
                studentTask.getFeedback()

        );
    }

    public static StudentTask toStudentTask(StudentTaskEntity studentTaskEntity){
        return new StudentTask(
                StudentMapper.toStudent(studentTaskEntity.getStudentEntity()),
                TaskMapper.toTask(studentTaskEntity.getTaskEntity()),
                studentTaskEntity.getStatus(),
                studentTaskEntity.getAssignedDate(),
                studentTaskEntity.getCulminationDate(),
                studentTaskEntity.getGrade(),
                studentTaskEntity.getFeedback()
        );
    }
}
