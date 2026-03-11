package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Adviser;

import java.util.List;
import java.util.Optional;

public interface AdviserRepositoryPort {
    Adviser saveAdviser(Adviser adviser);
    List<Adviser> getAllAdvisers();

}
