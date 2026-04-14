package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Admin;
import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Student;

import java.util.List;
import java.util.Optional;

public interface AdviserRepositoryPort {
    Adviser saveAdviser(Adviser adviser);
    List<Adviser> getAllAdvisers();
    Optional<Adviser> findById(Long id);
    Optional<Adviser> findByEmail(String email);
    boolean uploadLogo(Long id, byte[] logo);
    boolean updateAdviserInformation(Long id, Adviser adviser);
    List<Adviser> getAdvisersByStudentEmail(String email);
    boolean checkStudentAdviserStatus(Long studentID, Long adviserID);

}
