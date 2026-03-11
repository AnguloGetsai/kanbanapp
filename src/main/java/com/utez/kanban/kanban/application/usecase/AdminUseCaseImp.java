package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.port.in.AdminUseCase;
import com.utez.kanban.kanban.domain.port.out.AdminRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.AdviserRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.BoardRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;

import java.util.List;


public class AdminUseCaseImp implements AdminUseCase {
    private final BoardRepositoryPort boardRepositoryPort;
    private final AdviserRepositoryPort adviserRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final AdminRepositoryPort adminRepositoryPort;
    public AdminUseCaseImp(AdviserRepositoryPort adviserRepositoryPort,
                           UserRepositoryPort userRepositoryPort,
                            AdminRepositoryPort   adminRepositoryPort,
                           BoardRepositoryPort boardRepositoryPort){
        this.adviserRepositoryPort = adviserRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.adminRepositoryPort = adminRepositoryPort;
        this.boardRepositoryPort = boardRepositoryPort;
    }




    @Override
    public List<Adviser> getAllAdvisers() {
        return adviserRepositoryPort.getAllAdvisers();
    }

    @Override
    public void registerAdviserUser(Adviser adviser) {
        // revisar que el email no este registrado
        String email = adviser.getUser().getEmail();

        if(userRepositoryPort.findUserEmail(email).isPresent()){
            throw new BusinessRuleViolationException("Email already registered");
        }

        // buscar el admin que esta creando el asesor
        Admin admin = adminRepositoryPort
                .findByEmail(adviser.getAdmin().getUser().getEmail())
                .orElseThrow(() -> new BusinessRuleViolationException("Admin not found"));

        // crear el usuario del asesor
        User user = new User();
        user.setEmail(adviser.getUser().getEmail());
        user.setRol(Rol.ADVISER.name());
        user.setStatus(true);
        User Usercreated = userRepositoryPort.saveUser(user);








        // pasar las fks a adviser
        adviser.setAdmin(admin);
        adviser.setUser(Usercreated);


        // buardar asesor en bd
        Adviser adviserCreated = adviserRepositoryPort.saveAdviser(adviser);

        // asignarle su tablero
        Board board = new Board(
                null,
                "Tittle of board",
                adviserCreated
                );

        boardRepositoryPort.createBard(board);


    }



}
