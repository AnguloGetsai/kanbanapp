package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.model.StudentTask;
import com.utez.kanban.kanban.domain.port.in.StudentUseCase;
import com.utez.kanban.kanban.domain.port.out.AdviserRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.StudentRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.StudentTaskRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class StudentUseCaseImp implements StudentUseCase {

    private final StudentRepositoryPort studentRepositoryPort;
    private final AdviserRepositoryPort adviserRepositoryPort;
    private final StudentTaskRepositoryPort studentTaskRepositoryPort;

    public StudentUseCaseImp(
            StudentRepositoryPort studentRepositoryPort,
            AdviserRepositoryPort adviserRepositoryPort,
            StudentTaskRepositoryPort studentTaskRepositoryPort

    ){
        this.studentRepositoryPort = studentRepositoryPort;
        this.adviserRepositoryPort = adviserRepositoryPort;
        this.studentTaskRepositoryPort = studentTaskRepositoryPort;
    }


    @Override
    public List<Adviser> getMyAdvisers(String email) {
        return adviserRepositoryPort.getAdvisersByStudentEmail(email);
    }

    @Override
    public void updateStudentName(String email, String firstName, String lastName) {
        Student student = studentRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFirstName(firstName);
        student.setLastName(lastName);

        studentRepositoryPort.saveStudent(student);
    }

    @Override
    public void updateStudentImage(String email, byte[] image) {
        Student student = studentRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setImage(image);

        studentRepositoryPort.saveStudent(student);
    }

    @Override
    public Optional<Student> getStudentInformation(String email) {
        return studentRepositoryPort.findByEmail(email);
    }

    @Override
    public List<StudentTask> getTasksByAdviser(String email, Long adviserID) {
        return studentTaskRepositoryPort.getTasksByStudentAndAdviser(email, adviserID);
    }

    @Override
    public Optional<StudentTask> getTaskDetail(String email, Long taskID) {
        return studentTaskRepositoryPort.getTaskDetail(email, taskID);
    }
}
