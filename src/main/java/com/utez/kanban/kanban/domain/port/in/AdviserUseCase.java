package com.utez.kanban.kanban.domain.port.in;


import com.utez.kanban.kanban.domain.model.Student;

import java.util.List;

public interface AdviserUseCase {
    void registerStudent(String email, String firstName, String lastName);
    void addStudentToBoard(String adviserEmail,String studentEmail);
    List<Student> getAllStudents(String email);
    void disableBoardStudent(String email, Long studentID);
    void enableBoardStudent(String email, Long studentID);
}
