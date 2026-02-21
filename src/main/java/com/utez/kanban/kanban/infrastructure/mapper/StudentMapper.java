package com.utez.kanban.kanban.infrastructure.mapper;

import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.infrastructure.entity.StudentEntity;

public class StudentMapper {
    public static Student toStudent(StudentEntity studentEntity){
        return new Student(
                studentEntity.getStudentID(),
                studentEntity.getFirstName(),
                studentEntity.getLastName(),
                studentEntity.getGender(),
                studentEntity.getImage()
        );
    }

    public static StudentEntity toStudentEntity(Student student){
        return new StudentEntity(
                student.getFirstName(),
                student.getLastName(),
                student.getGender(),
                student.getImage()
        );
    }
}
