package com.utez.kanban.kanban.infrastructure.repository;

public class JpaAdviserRepositoryAdapter {
    private final JpaAdviserRepository jpaAdviserRepository;

    public JpaAdviserRepositoryAdapter(JpaAdviserRepository jpaAdviserRepository){
        this.jpaAdviserRepository = jpaAdviserRepository;

        // Codigo para operaciones con el administrador
    }
}
