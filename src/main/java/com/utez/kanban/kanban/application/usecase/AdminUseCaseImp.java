package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.model.exeption.user.EmailAlreadyExistsException;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.domain.port.in.AdminUseCase;
import com.utez.kanban.kanban.domain.port.out.*;

import java.util.List;
import java.util.Optional;


public class AdminUseCaseImp implements AdminUseCase {
    private final BoardRepositoryPort boardRepositoryPort;
    private final AdviserRepositoryPort adviserRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final AdminRepositoryPort adminRepositoryPort;
    private final TaskRepositoryPort taskRepositoryPort;
    public AdminUseCaseImp(AdviserRepositoryPort adviserRepositoryPort,
                           UserRepositoryPort userRepositoryPort,
                            AdminRepositoryPort   adminRepositoryPort,
                           BoardRepositoryPort boardRepositoryPort,
                           TaskRepositoryPort taskRepositoryPort){
        this.adviserRepositoryPort = adviserRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.adminRepositoryPort = adminRepositoryPort;
        this.boardRepositoryPort = boardRepositoryPort;
        this.taskRepositoryPort = taskRepositoryPort;
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

    @Override
    public Optional<Admin> getAdminInformation(String email) {
        return adminRepositoryPort.findByEmail(email);
    }

    @Override
    public void uploadLogo(String email, byte[] image) {
        Admin admin = adminRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        adminRepositoryPort.updateLogo(admin.getAdminID(), image);

    }

    @Override
    public Optional<Admin> findByEmail(String email) {
        return adminRepositoryPort.findByEmail(email);
    }

    @Override
    public List<Board> getAllBoards() {
        return boardRepositoryPort.getAllBoards();
    }

    @Override
    public void updateAdminInformation(String email, Admin admin) {
        Admin foundAdmin = adminRepositoryPort.findByEmail(email)
                        .orElseThrow(() -> new UserNotFoundException("Admin not found"));
        adminRepositoryPort.updateAdminInformation(foundAdmin.getAdminID(), admin);

//        // cambiar emali
//        if(userRepositoryPort.findUserEmail(admin.getUser().getEmail()).isPresent()){
//            throw  new EmailAlreadyExistsException("Email already exists");
//        }
//
//        if(!userRepositoryPort.changeEmail(email, admin.getUser().getEmail())){
//            throw new BusinessRuleViolationException("Error changing email");
//        }
    }

    @Override
    public List<Task> getTasksByAdviser(Long adviserId, String adminEmail) {

        Admin admin = adminRepositoryPort.findByEmail(adminEmail)
                .orElseThrow(() -> new UserNotFoundException("Admin not found"));


        Adviser adviser = adviserRepositoryPort.findById(adviserId)
                .orElseThrow(() -> new UserNotFoundException("Adviser not found"));


        if(!adviser.getAdmin().getAdminID().equals(admin.getAdminID())){
            throw new BusinessRuleViolationException("Unauthorized");
        }


        return taskRepositoryPort.findTasksByAdviserID(adviserId);
    }


}
