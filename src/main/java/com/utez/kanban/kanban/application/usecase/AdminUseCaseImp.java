package com.utez.kanban.kanban.application.usecase;

import com.utez.kanban.kanban.domain.model.Admin;
import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Rol;
import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.domain.model.exeption.user.BusinessRuleViolationException;
import com.utez.kanban.kanban.domain.port.in.AdminUseCase;
import com.utez.kanban.kanban.domain.port.out.AdminRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.AdviserRepositoryPort;
import com.utez.kanban.kanban.domain.port.out.UserRepositoryPort;
import jdk.swing.interop.SwingInterOpUtils;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.aspectj.weaver.SourceContextImpl;

import java.util.List;
import java.util.Optional;

public class AdminUseCaseImp implements AdminUseCase {

    private final AdviserRepositoryPort adviserRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final AdminRepositoryPort adminRepositoryPort;
    public AdminUseCaseImp(AdviserRepositoryPort adviserRepositoryPort,
                           UserRepositoryPort userRepositoryPort,
                            AdminRepositoryPort   adminRepositoryPort){
        this.adviserRepositoryPort = adviserRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.adminRepositoryPort = adminRepositoryPort;
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
        // crear el usuario del asesor
        User user = new User();
        user.setEmail(adviser.getUser().getEmail());
        user.setRol(Rol.ADVISER.name());
        User Usercreated = userRepositoryPort.saveUser(user);

        // buscar el admin que esta creando el asesor

        Admin admin = adminRepositoryPort
                .findByEmail(adviser.getAdmin().getUser().getEmail())
                .orElseThrow(() -> new BusinessRuleViolationException("Admin not found"));

        System.out.println("Datos del admin");// la consulta devuelve null necesita arreglo
        System.out.println(admin.getAdminID());
        System.out.println(admin.getUser().getEmail());
        System.out.println(admin.getUser().getUserID());


        // pasar las fks a adviser
        adviser.setAdmin(admin);
        adviser.setUser(Usercreated);

        adviserRepositoryPort.saveAdviser(adviser);


    }



}
