package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.Admin;
import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Board;
import com.utez.kanban.kanban.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface AdminUseCase {
    List<Adviser> getAllAdvisers();
    void registerAdviserUser(Adviser adviser);
    Optional<Admin> getAdminInformation(String email);
    void uploadLogo(String email, byte[] image);
    Optional<Admin> findByEmail(String email);
    List<Board> getAllBoards();
}
