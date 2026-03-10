package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.AdminService;
import com.utez.kanban.kanban.domain.model.Admin;
import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.User;
import com.utez.kanban.kanban.infrastructure.controller.DTO.AdvisorRegistration;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.apache.commons.lang3.concurrent.TimedSemaphore;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToUrl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/registerAdvisorUser")
    public ResponseEntity<?> createUserAdviser(@RequestBody @Valid AdvisorRegistration advisorRegistration,
                                               Authentication authentication){
        Adviser adviser = new Adviser();


        adviser.setFirstName(advisorRegistration.getFirstName());
        adviser.setLastName(advisorRegistration.getLastName());
        adviser.setUser(new User(advisorRegistration.getEmail()));


        Admin admin = new Admin();
        admin.setUser(new User(authentication.getName()));

        adviser.setAdmin(admin);


        adminService.registerAdviserUser(adviser);
        return ResponseEntity.ok("Registered advisor user");
    }


}
