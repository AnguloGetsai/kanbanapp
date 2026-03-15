package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.model.exeption.user.EmailAlreadyExistsException;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.domain.port.in.AdviserUseCase;
import com.utez.kanban.kanban.domain.port.out.AdviserRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.AdviserStudentRepository;
import com.utez.kanban.kanban.domain.port.out.StudentRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;

import java.util.List;

public class AdviserUseCaseImp implements AdviserUseCase {
    private final AdviserRepositoryPort adviserRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final StudentRepositoryPort studentRepositoryPort;
    private final AdviserStudentRepository adviserStudentRepository;

    public AdviserUseCaseImp(AdviserRepositoryPort adviserRepositoryPort,
                             UserRepositoryPort userRepositoryPort,
                             StudentRepositoryPort studentRepositoryPort,
                             AdviserStudentRepository adviserStudentRepository){
        this.adviserRepositoryPort = adviserRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.studentRepositoryPort = studentRepositoryPort;
        this.adviserStudentRepository = adviserStudentRepository;
    }


    @Override
    public void registerStudent(String email, String firstName, String lastName) {
        if(userRepositoryPort.findUserEmail(email).isPresent()){
            throw  new EmailAlreadyExistsException("User already exists");
        }


        // crear su usuario
        User user = new User();
        user.setEmail(email);
        user.setRol(Rol.STUDENT.name());
        User studentUser = userRepositoryPort.saveUser(user);
        if(studentUser == null) throw  new BusinessRuleViolationException("An error occurred while creating the user");

        // crear el student
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUser(studentUser);


        Student studentCreated = studentRepositoryPort.saveStudent(student);
        if(studentCreated == null)throw  new BusinessRuleViolationException("Student not created");
    }

    @Override
    public void addStudentToBoard(String adviserEmail, String studentEmail) {
        Adviser adviser = adviserRepositoryPort.findByEmail(adviserEmail)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        Student student = studentRepositoryPort.findByEmail(studentEmail)
                .orElseThrow(() -> new UserNotFoundException("Student not found"));

        AdviserStudent adviserStudent = new AdviserStudent(adviser, student, true);

        adviserStudentRepository.addStudentToBoard(adviserStudent);
    }

    @Override
    public List<Student> getAllStudents(String email) {
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));
        return adviserStudentRepository.getAllStudents(adviser.getAdviserID());
    }

    @Override
    public void disableBoardStudent(String email, Long studentID) {
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        studentRepositoryPort.findById(studentID)
                .orElseThrow(() -> new BusinessRuleViolationException("Student not found"));
        if(adviserStudentRepository.changeStatus(false, adviser.getAdviserID(), studentID)){
            return;
        }
        throw new BusinessRuleViolationException("Changes not applied");
    }

    @Override
    public void enableBoardStudent(String email, Long studentID) {
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        studentRepositoryPort.findById(studentID)
                .orElseThrow(() -> new BusinessRuleViolationException("Student not found"));
        if(adviserStudentRepository.changeStatus(true, adviser.getAdviserID(), studentID)){
            return;
        }
        throw new BusinessRuleViolationException("Changes not applied");
    }


}
