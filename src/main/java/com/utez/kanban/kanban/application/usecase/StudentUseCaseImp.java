package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.domain.port.in.StudentUseCase;
import com.utez.kanban.kanban.domain.port.out.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class StudentUseCaseImp implements StudentUseCase {

    private final StudentRepositoryPort studentRepositoryPort;
    private final AdviserRepositoryPort adviserRepositoryPort;
    private final StudentTaskRepositoryPort studentTaskRepositoryPort;
    private final EvidenceRepositoryPort evidenceRepositoryPort;
    private final EvidenceFileRepositoryPort evidenceFileRepositoryPort;


    public StudentUseCaseImp(
            StudentRepositoryPort studentRepositoryPort,
            AdviserRepositoryPort adviserRepositoryPort,
            StudentTaskRepositoryPort studentTaskRepositoryPort,
            EvidenceRepositoryPort evidenceRepositoryPort,
            EvidenceFileRepositoryPort  evidenceFileRepositoryPort

    ){
        this.studentRepositoryPort = studentRepositoryPort;
        this.adviserRepositoryPort = adviserRepositoryPort;
        this.studentTaskRepositoryPort = studentTaskRepositoryPort;
        this.evidenceRepositoryPort = evidenceRepositoryPort;
        this.evidenceFileRepositoryPort = evidenceFileRepositoryPort;
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

    @Override
    public void submitEvidence(String email, Long taskID, String comment, List<MultipartFile> files) {

        Student student = studentRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Student not found"));


        StudentTask studentTask = studentTaskRepositoryPort
                .findByStudentAndTask(student.getStudentID(), taskID)
                .orElseThrow(() -> new BusinessRuleViolationException("Task not assigned"));


        Evidence evidence = new Evidence();
        evidence.setUploadDate(LocalDate.now());
        evidence.setComment(comment);
        evidence.setStudentTask(studentTask);

        Evidence savedEvidence = evidenceRepositoryPort.save(evidence);


        if(files != null && !files.isEmpty()){
            List<EvidenceFile> evidenceFiles = files.stream().map(f -> {
                try{
                    EvidenceFile ef = new EvidenceFile();
                    ef.setFileName(f.getOriginalFilename());
                    ef.setFileType(f.getContentType());
                    ef.setFileData(f.getBytes());
                    ef.setEvidence(savedEvidence);

                    return ef;
                }catch (Exception e){
                    throw new RuntimeException("Error processing file");
                }
            }).toList();

            evidenceFileRepositoryPort.saveAll(evidenceFiles);
        }


        studentTask.setStatus("Done");
        studentTask.setCulminationDate(LocalDate.now());

        studentTaskRepositoryPort.save(studentTask);
    }

    @Override
    public void changeTaskStatus(String email, Long taskID, String status) {

        Student student = studentRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Student not found"));


        StudentTask studentTask = studentTaskRepositoryPort
                .findByStudentAndTask(student.getStudentID(), taskID)
                .orElseThrow(() -> new BusinessRuleViolationException("Task not assigned"));


        StatusKanban newStatus;
        try {
            newStatus = StatusKanban.valueOf(status);
        } catch (Exception e){
            throw new BusinessRuleViolationException("Invalid status");
        }


        String current = studentTask.getStatus();

        if(current.equals(StatusKanban.ToDo.name()) && newStatus == StatusKanban.Doing){

        }
        else if(current.equals(StatusKanban.Doing.name()) && newStatus == StatusKanban.Done){

        }
        else if(current.equals(StatusKanban.ToDo.name()) && newStatus == StatusKanban.Done){

        }
        else if(current.equals(newStatus.name())){

            return;
        }
        else{
            throw new BusinessRuleViolationException("Invalid state transition");
        }


        studentTask.setStatus(newStatus.name());

        if(newStatus == StatusKanban.Done){
            studentTask.setCulminationDate(LocalDate.now());
        }

        studentTaskRepositoryPort.save(studentTask);
    }
}
