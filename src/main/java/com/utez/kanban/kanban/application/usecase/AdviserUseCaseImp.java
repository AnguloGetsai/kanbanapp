package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.model.exeption.user.EmailAlreadyExistsException;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.domain.port.in.AdviserUseCase;
import com.utez.kanban.kanban.domain.port.out.*;

import com.utez.kanban.kanban.domain.model.Evidence;
import com.utez.kanban.kanban.infrastructure.controller.DTO.AdviserReportDto;
import com.utez.kanban.kanban.infrastructure.controller.DTO.StudentExpedienteDto;
import com.utez.kanban.kanban.infrastructure.controller.DTO.TaskDetailReportDto;

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
        user.setStatus(true);
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
            // 1. Obtener las asignaciones actuales de la base de datos
            List<StudentTask> currentAssignments = studentTaskRepositoryPort.findByTaskId(taskID);

            // Sacar solo los IDs de los alumnos que ya estaban asignados
            Set<Long> currentStudentIDs = currentAssignments.stream()
                    .map(st -> st.getStudent().getStudentID())
                    .collect(java.util.stream.Collectors.toSet());

            Set<Long> newStudentIDs = new HashSet<>(studentIDs);

            // 2. Identificar a quiénes QUITAR (estaban antes, pero ya no vienen en la nueva lista)
            for (StudentTask st : currentAssignments) {
                if (!newStudentIDs.contains(st.getStudent().getStudentID())) {
                    // Solo borramos la relación de este alumno en específico
                    studentTaskRepositoryPort.deleteStudentTask(st);
                }
            }

            // 3. Identificar a quiénes AGREGAR (vienen en la nueva lista, pero no estaban antes)
            List<Student> students = studentRepositoryPort.getStudentByAdviserID(adviser.getAdviserID());

            List<StudentTask> newRelations = students.stream()
                    // Filtramos: Que esté en la lista nueva Y QUE NO estuviera ya asignado antes
                    .filter(s -> newStudentIDs.contains(s.getStudentID()) && !currentStudentIDs.contains(s.getStudentID()))
                    .map(s -> {
                        StudentTask st = new StudentTask();
                        st.setTask(existingTask);
                        st.setStudent(s);
                        st.setAssignedDate(LocalDate.now());
                        st.setStatus(existingTask.getStatusKanban());
                        return st;
                    }).toList();

            // 4. Guardar SOLO a los nuevos
            if (!newRelations.isEmpty()) {
                studentTaskRepositoryPort.saveAll(newRelations);
            }
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

    @Override
    public AdviserReportDto getAdviserReport(String email, LocalDate startDate, LocalDate endDate) {
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        // Llamamos a la función genérica
        return calculateReportForAdviser(adviser, startDate, endDate);
    }

    @Override
    public StudentExpedienteDto getStudentExpediente(String email, Long studentID, LocalDate startDate, LocalDate endDate) {
        // 1. Validar Asesor y Alumno
        Adviser adviser = adviserRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        Student student = studentRepositoryPort.findById(studentID)
                .orElseThrow(() -> new UserNotFoundException("Student not found"));

        // 2. Obtener todas las tareas asignadas a ESTE estudiante
        List<Task> adviserTasks = taskRepositoryPort.findTasksByAdviserID(adviser.getAdviserID());

        int todo = 0, doing = 0, done = 0;
        double totalGrades = 0.0;
        int gradedCount = 0;
        int onTimeCount = 0;
        int finishedTasksCount = 0;

        List<TaskDetailReportDto> taskHistory = new ArrayList<>();

        for (Task task : adviserTasks) {
            // Buscamos si el alumno está en esta tarea
            Optional<StudentTask> stOptional = studentTaskRepositoryPort.findByStudentAndTask(studentID, task.getTaskID());

            if (stOptional.isPresent()) {
                StudentTask st = stOptional.get();
                LocalDate taskDate = st.getAssignedDate();

                // Filtro de fechas
                if ((taskDate.isAfter(startDate) || taskDate.isEqual(startDate)) &&
                        (taskDate.isBefore(endDate) || taskDate.isEqual(endDate))) {

                    // Conteo de Estatus
                    if (st.getStatus() != null) {
                        switch (st.getStatus().toUpperCase()) {
                            case "TODO": todo++; break;
                            case "DOING": doing++; break;
                            case "DONE": done++; break;
                        }
                    }

                    // Cálculo de Promedio
                    if (st.getGrade() != null) {
                        totalGrades += st.getGrade();
                        gradedCount++;
                    }

                    // Cálculo de Puntualidad (Solo evaluamos las que ya culminó)
                    if (st.getCulminationDate() != null) {
                        finishedTasksCount++;
                        // Si la culminó antes o el mismo día del límite
                        if (!st.getCulminationDate().isAfter(task.getLimitDate())) {
                            onTimeCount++;
                        }
                    }

                    // Agregar a su historial
                    taskHistory.add(new TaskDetailReportDto(
                            task.getName(),
                            student.getFirstName() + " " + student.getLastName(),
                            st.getAssignedDate(),
                            st.getStatus(),
                            st.getGrade()
                    ));
                }
            }
        }

        // Matemáticas finales
        Double avgGrade = (gradedCount > 0) ? (totalGrades / gradedCount) : 0.0;
        avgGrade = Math.round(avgGrade * 100.0) / 100.0;

        Double onTimePct = (finishedTasksCount > 0) ? ((double) onTimeCount / finishedTasksCount) * 100 : 0.0;
        onTimePct = Math.round(onTimePct * 100.0) / 100.0;


        Integer age = 0;



        String fullName = student.getFirstName() + " " + student.getLastName();
        int totalTasks = todo + doing + done;

        return new StudentExpedienteDto(
                fullName,
                student.getUser().getEmail(),
                age,
                true,
                totalTasks, todo, doing, done, avgGrade, onTimePct, taskHistory
        );
    }

    @Override
    public AdviserReportDto getAdviserReportById(Long adviserID, LocalDate startDate, LocalDate endDate) {
        Adviser adviser = adviserRepositoryPort.findById(adviserID) // Usamos el ID en lugar del email
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));

        // Llamamos a la MISMA función genérica
        return calculateReportForAdviser(adviser, startDate, endDate);
    }

    private AdviserReportDto calculateReportForAdviser(Adviser adviser, LocalDate startDate, LocalDate endDate) {
        int totalStudents = adviserStudentRepository.getAllStudents(adviser.getAdviserID()).size();
        List<Task> adviserTasks = taskRepositoryPort.findTasksByAdviserID(adviser.getAdviserID());

        int todo = 0, doing = 0, done = 0, totalFilteredTasks = 0;
        double totalGrades = 0.0;
        int gradedCount = 0;

        List<TaskDetailReportDto> detailReportDtoList = new ArrayList<>();

        for (Task task : adviserTasks) {
            List<StudentTask> studentTasks = studentTaskRepositoryPort.findByTaskId(task.getTaskID());

            for (StudentTask st : studentTasks) {
                LocalDate taskDate = st.getAssignedDate();

                if ((taskDate.isAfter(startDate) || taskDate.isEqual(startDate)) &&
                        (taskDate.isBefore(endDate) || taskDate.isEqual(endDate))) {

                    totalFilteredTasks++;

                    if (st.getStatus() != null) {
                        switch (st.getStatus().toUpperCase()) {
                            case "TODO": todo++; break;
                            case "DOING": doing++; break;
                            case "DONE": done++; break;
                        }
                    }

                    if (st.getGrade() != null) {
                        totalGrades += st.getGrade();
                        gradedCount++;
                    }

                    String fullName = st.getStudent().getFirstName() + " " + st.getStudent().getLastName();

                    TaskDetailReportDto detail = new TaskDetailReportDto(
                            task.getName(),
                            fullName,
                            st.getAssignedDate(),
                            st.getStatus(),
                            st.getGrade()
                    );
                    detailReportDtoList.add(detail);
                }
            }
        }

        Double avg = (gradedCount > 0) ? (totalGrades / gradedCount) : 0.0;
        avg = Math.round(avg * 100.0) / 100.0;

        return new AdviserReportDto(totalStudents, totalFilteredTasks, todo, doing, done, avg, detailReportDtoList);
    }


}
