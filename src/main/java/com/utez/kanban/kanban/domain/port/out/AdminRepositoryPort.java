package com.utez.kanban.kanban.domain.port.out;

import com.utez.kanban.kanban.domain.model.Admin;

import java.util.Optional;

public interface AdminRepositoryPort {
    void createAdmin(Admin admin);
    Optional<Admin> findById(Long adminID);
    Optional<Admin> update(Admin admin);
    boolean delete(Long adminID);
    Optional<Admin> findByEmail(String email);

}
