package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Adviser;

import java.util.List;

public interface AdviserRepositoryPort {

    List<Adviser> getAllAdvisers();

    Adviser saveAdviser(Adviser adviser);

    java.util.Optional<Adviser> findById(Long id);

    java.util.Optional<Adviser> findByEmail(String email);

    boolean uploadLogo(Long id, byte[] logo);

    boolean updateAdviserInformation(Long id, Adviser adviser);
}