package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.model.StudentTask;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface StudentUseCase {
    List<Adviser> getMyAdvisers(String email);
    void updateStudentName(String email, String firstName, String lastName);
    void updateStudentImage(String email, byte[] image);
    Optional<Student> getStudentInformation(String email);
    List<StudentTask> getTasksByAdviser(String email, Long adviserID);
    Optional<StudentTask> getTaskDetail(String email, Long taskID);
    void submitEvidence(String email, Long taskID, String comment, List<MultipartFile> files);
    void changeTaskStatus(String email, Long taskID, String status);
}
