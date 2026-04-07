package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.AdminService;
import com.utez.kanban.kanban.application.service.StudentTaskService;
import com.utez.kanban.kanban.domain.model.*;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.infrastructure.controller.DTO.*;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@RestController
@Validated
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final StudentTaskService studentTaskService;

    public AdminController(AdminService adminService, StudentTaskService studentTaskService){
        this.adminService = adminService;
        this.studentTaskService = studentTaskService;
    }

    // *|* *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*

    //           TODOS LOS ENDPOINTS QUE TE PIDAN COMO PARAMETRO AUTHENTICATION NO LE PASES NADA       //
    //                                          ESO LO INYECTA SPRING.

    //  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*  *|*


    //solo manda firstName, lastName and email
    @PostMapping("/registerAdvisorUser")
    public ResponseEntity<?> createUserAdviser(@RequestBody @Valid AdvisorRegistration advisorRegistration,
                                               Authentication authentication // authentication no se manda
    ){
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




    @GetMapping("/getAllAdvisers")
    public List<AdviserInformation> getAllAdvisers(){
        List<AdviserInformation> adviserInformationList = new ArrayList<>();
        for(Adviser a: adminService.getAllAdvisers()){

            adviserInformationList.add(AdviserInformation.toAdviserInformation(a));
        }


        return adviserInformationList;
    }


    // manda el solo la imagen
    @PostMapping("/uploadLogo")
    public ResponseEntity<?> uploadLogo(
            @RequestParam("file") MultipartFile file,
            Authentication authentication
    ){
        try{
            String email = authentication.getName();
            adminService.uploadLogo(email, file.getBytes());
            return ResponseEntity.ok(new SuccessResponse(200,"Logo was saved"));
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping("/getAdminInformation")
    public ResponseEntity<?> getAdminInformation(Authentication authentication){
        String email = authentication.getName();
        Admin  admin = adminService.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));


        return ResponseEntity.ok(AdminInformation.toAdminInformation(admin));
    }


    @GetMapping("/getAllBoards")
    public ResponseEntity<List<BoardCard>> getAllBoards(){
        List<BoardCard> boardCardList = new ArrayList<>();
        for(Board board: adminService.getAllBoards()){
            boardCardList.add(BoardCard.toBoardCard(board));
        }
        return ResponseEntity.ok(boardCardList);
    }


    // solo manda firstName and lastName
    @PostMapping("/updateAdminInformation")
    public ResponseEntity<?> updateAdminInformation(
            @RequestBody @Valid AdminInformation adminInformation
            ,Authentication authentication){
        adminService.updateAdminInformation(authentication.getName(),AdminInformation.toAdmin(adminInformation) );
        return ResponseEntity.ok(new SuccessResponse(
                201,
                "Updated admin"
        ));

    }

    @GetMapping("/adviser/{adviserId}/tasks")
    public ResponseEntity<?> getTasksByAdviser(
            @PathVariable Long adviserId,
            Authentication authentication
    ) {

        List<Task> tasks = adminService.getTasksByAdviser(adviserId, authentication.getName());


        List<TaskSimpleDto> response = tasks.stream().map(task -> {
            List<StudentTask> st = studentTaskService.findByTaskId(task.getTaskID());
            return TaskSimpleDto.fromDomain(task, st);
        }).toList();

        return ResponseEntity.ok(response);
    }


}
