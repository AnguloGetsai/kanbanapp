package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.AdminService;
import com.utez.kanban.kanban.domain.model.User;
import jakarta.validation.constraints.Email;
import org.apache.commons.lang3.concurrent.TimedSemaphore;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/registerAdvisorUser")
    public ResponseEntity<?> createUserAdviser(
            @RequestParam @Email String email
    ){
        adminService.registerAdviserUser(new User(email));
        return ResponseEntity.ok("Registered advisor user");
    }


}
