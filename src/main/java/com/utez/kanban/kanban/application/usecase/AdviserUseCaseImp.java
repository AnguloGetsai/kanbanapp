package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.model.exeption.user.EmailAlreadyExistsException;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.domain.port.in.AdviserUseCase;
import com.utez.kanban.kanban.domain.port.out.*;

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

    public AdviserUseCaseImp(
            AdviserRepositoryPort adviserRepositoryPort,
            UserRepositoryPort userRepositoryPort,
            StudentRepositoryPort studentRepositoryPort,
            AdviserStudentRepository adviserStudentRepository,
            TaskRepositoryPort taskRepositoryPort,
            BoardRepositoryPort boardRepositoryPort,
            AttachmentRepositoryPort attachmentRepositoryPort,
            StudentTaskRepositoryPort studentTaskRepositoryPort
    ) {
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

        if (userRepositoryPort.findUserEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException("User already exists");
        }

        // crear usuario
        User user = new User();
        user.setEmail(email);
        user.setRol(Rol.STUDENT.name());

        User studentUser = userRepositoryPort.saveUser(user);
        if (studentUser == null) {
            throw new BusinessRuleViolationException("Error creating user");
        }

        // crear student
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUser(studentUser);

        Student studentCreated = studentRepositoryPort.saveStudent(student);
        if (studentCreated == null) {
            throw new BusinessRuleViolationException("Student not created");
        }
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

        if (!adviserStudentRepository.changeStatus(false, adviser.getAdviserID(), studentID)) {
            throw new BusinessRuleViolationException("Changes not applied");
        }
    }

    @Override
    public void enableBoardStudent(String email, Long studentID) {

        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        studentRepositoryPort.findById(studentID)
                .orElseThrow(() -> new BusinessRuleViolationException("Student not found"));

        if (!adviserStudentRepository.changeStatus(true, adviser.getAdviserID(), studentID)) {
            throw new BusinessRuleViolationException("Changes not applied");
        }
    }

    @Override
    public void uploadLogo(String email, byte[] logo) {

        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        adviserRepositoryPort.uploadLogo(adviser.getAdviserID(), logo);
    }

    @Override
    public void updateAdviserInformation(String email, Adviser adviser) {

        Adviser found = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        if (!adviserRepositoryPort.updateAdviserInformation(found.getAdviserID(), adviser)) {
            throw new BusinessRuleViolationException("Error updating adviser");
        }
    }

    @Override
    public Optional<Adviser> getAdviserInformation(String email) {

        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        return Optional.of(adviser);
    }

    @Override
    public void createTask(List<Long> studentIDs, Task task, String email, List<Attachment> files) {

        // buscar adviser
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));

        // buscar board
        Board board = boardRepositoryPort.findBoardByAdviserId(adviser.getAdviserID())
                .orElseThrow(() -> new BusinessRuleViolationException("BOARD NOT FOUND"));

        // crear tarea
        task.setBoard(board);
        Task createdTask = taskRepositoryPort.save(task);

        if (createdTask == null) {
            throw new BusinessRuleViolationException("Error creating task");
        }

        // guardar archivos
        if (files != null && !files.isEmpty()) {
            List<Attachment> attachmentList = files.stream().map(f -> {
                Attachment attachment = new Attachment();
                attachment.setTask(createdTask);
                attachment.setFileName(f.getFileName());
                attachment.setFileType(f.getFileType());
                attachment.setFileData(f.getFileData());
                return attachment;
            }).toList();

            attachmentRepositoryPort.saveAll(attachmentList);
        }

        // asignar estudiantes
        if (studentIDs == null || studentIDs.isEmpty()) return;

        List<Student> studentList = studentRepositoryPort.getStudentByAdviserID(adviser.getAdviserID());
        if (studentList == null) return;

        Set<Long> ids = new HashSet<>(studentIDs);

        List<StudentTask> studentTasks = studentList.stream()
                .filter(s -> ids.contains(s.getStudentID()))
                .map(s -> {
                    StudentTask st = new StudentTask();
                    st.setStudent(s);
                    st.setTask(createdTask);
                    st.setAssignedDate(createdTask.getCreationDate());
                    st.setStatus(createdTask.getStatusKanban());
                    return st;
                }).toList();

        studentTaskRepositoryPort.saveAll(studentTasks);
    }
}