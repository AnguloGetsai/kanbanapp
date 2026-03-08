package com.utez.kanban.kanban.domain.port.in;

import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface AdminUseCase {
    List<Adviser> getAllAdvisers();
    void registerAdviserUser(User user);
}
