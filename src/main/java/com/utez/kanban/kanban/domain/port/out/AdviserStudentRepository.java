package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.AdviserStudent;
import com.utez.kanban.kanban.domain.model.Student;

import java.util.List;

public interface AdviserStudentRepository {
    void addStudentToBoard(AdviserStudent adviserStudent);
    List<Student> getAllStudents(Long id);
    boolean changeStatus(boolean status, Long adviserID, Long studentID);

    // Obtiene los asesores a los que un estudiante está registrado (usando ADVISER_STUDENT.status)
    List<Adviser> getAdvisersByStudent(Long studentId);

    // Valida acceso al tablero del adviser para ese estudiante
    boolean isAdviserActiveForStudent(Long adviserId, Long studentId);
}
