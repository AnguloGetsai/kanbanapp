package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.model.exeption.user.EmailAlreadyExistsException;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.domain.port.in.AdviserUseCase;
import com.utez.kanban.kanban.domain.port.out.*;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;

public class AdviserUseCaseImp implements AdviserUseCase {
    private final AdviserRepositoryPort adviserRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final StudentRepositoryPort studentRepositoryPort;
    private final AdviserStudentRepository adviserStudentRepository;
    private final TaskRepositoryPort taskRepositoryPort;
    private final BoardRepositoryPort boardRepositoryPort;
    private final AttachmentRepositoryPort attachmentRepositoryPort;
    private final StudentTaskRepositoryPort studentTaskRepositoryPort;

    public AdviserUseCaseImp(AdviserRepositoryPort adviserRepositoryPort,
                             UserRepositoryPort userRepositoryPort,
                             StudentRepositoryPort studentRepositoryPort,
                             AdviserStudentRepository adviserStudentRepository,
                             TaskRepositoryPort taskRepositoryPort,
                             BoardRepositoryPort boardRepositoryPort,
                             AttachmentRepositoryPort attachmentRepositoryPort,
                             StudentTaskRepositoryPort studentTaskRepositoryPort){
        this.adviserRepositoryPort = adviserRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.studentRepositoryPort = studentRepositoryPort;
        this.adviserStudentRepository = adviserStudentRepository;
        this.taskRepositoryPort = taskRepositoryPort;
        this.boardRepositoryPort = boardRepositoryPort;
        this.attachmentRepositoryPort = attachmentRepositoryPort;
        this.studentTaskRepositoryPort = studentTaskRepositoryPort;
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

    @Override
    public void uploadLogo(String email, byte[] logo) {
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));
        adviserRepositoryPort.uploadLogo(adviser.getAdviserID(), logo);
    }

    @Override
    public void updateAdviserInformation(String email, Adviser adviser) {
        Adviser foundAdviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        if(!adviserRepositoryPort.updateAdviserInformation(foundAdviser.getAdviserID(), adviser)){
            throw new BusinessRuleViolationException("Error updating advisor data");
        }
    }

    @Override
    public Optional<Adviser> getAdviserInformation(String email) {
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));
        return Optional.ofNullable(adviser);
    }

    @Override
    public void createTask(List<Long> studentIDs, Task task, String email, List<Attachment> files) {

        //buscar si el adviser existe
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));

        // buscar el board del asesor
        Board board = boardRepositoryPort.findBoardByAdviserId(adviser.getAdviserID())
                .orElseThrow(() -> new BusinessRuleViolationException("BOARD NOT FOUND"));

        // crear la tarea
        task.setBoard(board);
        Task createdTask = taskRepositoryPort.save(task)
                .orElseThrow(() -> new BusinessRuleViolationException("Error creating task"));

        // agregar los archivos adjuntos a la tarea

        for(Attachment file: files){
            file.setTask(createdTask);
            attachmentRepositoryPort.saveAttachment(file);
        }


        // agregar los estudiantes a la tarea
        if(studentIDs == null) return;

        List<Student> studentList = studentRepositoryPort.getStudentByAdviserID(adviser.getAdviserID());
        if(studentList == null) return;

        Set<Long> idsSet = new HashSet<>(studentIDs);
        List<Student> filtrados = studentList.stream()
                .filter(s -> idsSet.contains(s.getStudentID()))
                .toList();

        List<StudentTask> st = filtrados.stream()
                .map(s -> {
                     StudentTask studentTask = new StudentTask();
                     studentTask.setTask(createdTask);
                     studentTask.setStudent(s);
                     studentTask.setAssignedDate(createdTask.getCreationDate());
                     studentTask.setStatus(createdTask.getStatusKanban());
                     return studentTask;
                })
                .toList();

        studentTaskRepositoryPort.saveAll(st);




    }


}
