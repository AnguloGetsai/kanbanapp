package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.*;

import java.util.List;
import java.util.Optional;

public interface AdminUseCase {
    List<Adviser> getAllAdvisers();
    void registerAdviserUser(Adviser adviser);
    Optional<Admin> getAdminInformation(String email);
    void uploadLogo(String email, byte[] image);
    Optional<Admin> findByEmail(String email);
    List<Board> getAllBoards();
    void updateAdminInformation(String email, Admin admin);
    List<Task> getTasksByAdviser(Long adviserId, String adminEmail);
}
