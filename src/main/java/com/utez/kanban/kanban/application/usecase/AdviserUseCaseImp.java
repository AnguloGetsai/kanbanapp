package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.model.exeption.user.EmailAlreadyExistsException;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.domain.port.in.AdviserUseCase;
import com.utez.kanban.kanban.domain.port.out.*;

import com.utez.kanban.kanban.domain.model.Evidence;

import java.time.LocalDate;
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
    private final NotificationRepositoryPort notificationRepositoryPort;
    private final EvidenceRepositoryPort evidenceRepositoryPort;

    public AdviserUseCaseImp(AdviserRepositoryPort adviserRepositoryPort,
                             UserRepositoryPort userRepositoryPort,
                             StudentRepositoryPort studentRepositoryPort,
                             AdviserStudentRepository adviserStudentRepository,
                             TaskRepositoryPort taskRepositoryPort,
                             BoardRepositoryPort boardRepositoryPort,
                             AttachmentRepositoryPort attachmentRepositoryPort,
                             StudentTaskRepositoryPort studentTaskRepositoryPort,
                             NotificationRepositoryPort notificationRepositoryPort,
                             EvidenceRepositoryPort evidenceRepositoryPort){
        this.adviserRepositoryPort = adviserRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.studentRepositoryPort = studentRepositoryPort;
        this.adviserStudentRepository = adviserStudentRepository;
        this.taskRepositoryPort = taskRepositoryPort;
        this.boardRepositoryPort = boardRepositoryPort;
        this.attachmentRepositoryPort = attachmentRepositoryPort;
        this.studentTaskRepositoryPort = studentTaskRepositoryPort;
        this.notificationRepositoryPort = notificationRepositoryPort;
        this.evidenceRepositoryPort = evidenceRepositoryPort;
    }


    @Override
    public void registerStudent(String adviserEmail,String email, String firstName, String lastName) {
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

        Adviser adviser  = adviserRepositoryPort.findByEmail(adviserEmail)
                .orElseThrow(() -> new  UserNotFoundException("Adviser not found"));



            AdviserStudent adviserStudent = new AdviserStudent(adviser, studentCreated, true);
            adviserStudentRepository.addStudentToBoard(adviserStudent);


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


     //    pendiente por hacer filtro hash
        if(files != null && !files.isEmpty()){
            files.forEach(f -> f.setTask(createdTask));
            attachmentRepositoryPort.saveAll(files);
        }

        // agregar los estudiantes a la tarea

        if(studentIDs == null || studentIDs.isEmpty()){
            throw new BusinessRuleViolationException("Task must have at least one student");
        }

        List<Student> studentList = studentRepositoryPort.getStudentByAdviserID(adviser.getAdviserID());
        if(studentList.isEmpty()){
            throw new BusinessRuleViolationException("No students found for adviser");
        }

        Set<Long> idsSet = new HashSet<>(studentIDs);
        List<Student> filtrados = studentList.stream()
                .filter(s -> idsSet.contains(s.getStudentID()))
                .toList();
//modificacion temporal
        List<StudentTask> st = filtrados.stream()
                .map(s -> {
                     StudentTask studentTask = new StudentTask();
                     studentTask.setTask(createdTask);
                     studentTask.setStudent(s);
                     studentTask.setAssignedDate(createdTask.getCreationDate());
                     studentTask.setStatus(StatusKanban.ToDo.name());
                     return studentTask;
                })
                .toList();

        studentTaskRepositoryPort.saveAll(st);


        st.forEach(studentTask -> {
            Notification notification = new Notification();
            notification.setMessage("You have a new task: " + createdTask.getName());
            notification.setRead(false);
            notification.setStudentID(studentTask.getStudent().getStudentID());

            notificationRepositoryPort.save(notification);
        });




    }

    @Override
    public List<Task> getAllTasks(String email) {
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        return taskRepositoryPort.findTasksByAdviserID(adviser.getAdviserID());
    }

    @Override
    public void deleteTask(Long taskID, String email) {
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        Task task = taskRepositoryPort.findById(taskID)
                .orElseThrow(() -> new BusinessRuleViolationException("Task not found"));

        // VALIDAR QUE LA TAREA SEA DEL ASESOR
        if(!task.getBoard().getAdviser().getAdviserID().equals(adviser.getAdviserID())){
            throw new BusinessRuleViolationException("Unauthorized");
        }

        taskRepositoryPort.deleteTask(taskID);
    }

    @Override
    public void updateTask(Long taskID, String email, Task task, List<Long> studentIDs, List<Attachment> files) {
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        Task existingTask = taskRepositoryPort.findById(taskID)
                .orElseThrow(() -> new BusinessRuleViolationException("Task not found"));


        existingTask.setName(task.getName());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatusKanban(task.getStatusKanban());
        existingTask.setColor(task.getColor());
        existingTask.setPriority(task.getPriority());
        existingTask.setLimitDate(task.getLimitDate());

        taskRepositoryPort.update(existingTask);



        if(files != null){


            attachmentRepositoryPort.deleteByTaskId(taskID);

            if(!files.isEmpty()){
                files.forEach(f -> f.setTask(existingTask));
                attachmentRepositoryPort.saveAll(files);
            }
        }


        if(studentIDs != null){

            // eliminar relaciones actuales
            studentTaskRepositoryPort.deleteByTaskId(taskID);

            List<Student> students = studentRepositoryPort
                    .getStudentByAdviserID(adviser.getAdviserID());

            Set<Long> idsSet = new HashSet<>(studentIDs);

            List<StudentTask> newRelations = students.stream()
                    .filter(s -> idsSet.contains(s.getStudentID()))
                    .map(s -> {
                        StudentTask st = new StudentTask();
                        st.setTask(existingTask);
                        st.setStudent(s);
                        st.setAssignedDate(LocalDate.now());
                        st.setStatus(existingTask.getStatusKanban());
                        return st;
                    }).toList();

            studentTaskRepositoryPort.saveAll(newRelations);
        }
    }


    @Override
    public void updateTaskStatus(Long taskID, String email, String status) {
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        Task task = taskRepositoryPort.findById(taskID)
                .orElseThrow(() -> new BusinessRuleViolationException("Task not found"));

        // validar ownership
        if(!task.getBoard().getAdviser().getAdviserID()
                .equals(adviser.getAdviserID())){
            throw new BusinessRuleViolationException("Unauthorized");
        }

        // validar enum
        try {
            StatusKanban.valueOf(status);
        } catch (Exception e){
            throw new BusinessRuleViolationException("Invalid status");
        }

        task.setStatusKanban(status);

        taskRepositoryPort.save(task);
    }

    @Override
    public List<Evidence> getStudentEvidences(String email, Long taskID, Long studentID) {
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        Task task = taskRepositoryPort.findById(taskID)
                .orElseThrow(() -> new BusinessRuleViolationException("Task not found"));


        if(!task.getBoard().getAdviser().getAdviserID()
                .equals(adviser.getAdviserID())){
            throw new BusinessRuleViolationException("Unauthorized");
        }


        StudentTask st = studentTaskRepositoryPort
                .findByStudentAndTask(studentID, taskID)
                .orElseThrow(() -> new BusinessRuleViolationException("Student not assigned to this task"));


        return evidenceRepositoryPort.findByStudentTask(st);
    }

    @Override
    public void gradeStudentTask(String email, Long taskID, Long studentID, Double grade, String feedback) {
        // 1. Validar que el asesor exista
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        // 2. Validar que la tarea exista
        Task task = taskRepositoryPort.findById(taskID)
                .orElseThrow(() -> new BusinessRuleViolationException("Task not found"));

        // 3. Validar que la tarea pertenezca al tablero de ESTE asesor
        if(!task.getBoard().getAdviser().getAdviserID().equals(adviser.getAdviserID())){
            throw new BusinessRuleViolationException("Unauthorized to grade this task");
        }

        // 4. Buscar la relación específica entre el estudiante y esta tarea
        StudentTask studentTask = studentTaskRepositoryPort.findByStudentAndTask(studentID, taskID)
                .orElseThrow(() -> new BusinessRuleViolationException("Student is not assigned to this task"));

        // 5. Asignar la calificación y la retroalimentación
        studentTask.setGrade(grade);
        studentTask.setFeedback(feedback);

        // 6. Guardar los cambios v
        studentTaskRepositoryPort.save(studentTask);
    }


}
