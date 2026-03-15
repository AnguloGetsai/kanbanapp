package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.AdviserService;
import com.utez.kanban.kanban.application.service.StudentService;
import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.infrastructure.controller.DTO.AdviserStudentDTO;
import com.utez.kanban.kanban.infrastructure.controller.DTO.StudentDTO;
import com.utez.kanban.kanban.infrastructure.controller.DTO.StudentRegister;
import com.utez.kanban.kanban.infrastructure.controller.DTO.SuccessResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/adviser")
@Validated
public class AdviserController {
    private final AdviserService adviserService;

    public AdviserController(AdviserService adviserService){
        this.adviserService = adviserService;
    }
    // pendiente asignar el estudiante a tablero durante el registro
    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerStudent(@RequestBody @Valid StudentRegister studentRegister){
        adviserService.registerStudent(
                studentRegister.getEmail(),
                studentRegister.getFirstName(),
                studentRegister.getLastName());
        return ResponseEntity.ok(new SuccessResponse(
                200,
                "Student created correctly"
        ));
    }

    @PostMapping("/addStudentToBoard")
    public ResponseEntity<?> addStudentToBoard(
            @RequestBody @Valid AdviserStudentDTO adviserStudentDTO,
            Authentication authentication
            ){

        adviserService.addStudentToBoard(authentication.getName(), adviserStudentDTO.getEmail());
        return ResponseEntity.ok(new SuccessResponse(
                200,
                "User added to your board"
        ));
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentDTO>> getAllStudents(Authentication authentication){
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for(Student student: adviserService.getAllStudents(authentication.getName())){
            studentDTOList.add(StudentDTO.toStudentDTO(student));
        }
        return ResponseEntity.ok(studentDTOList);
    }

    @PostMapping("/disableBoardStudent")
    public ResponseEntity<?> disableBoardStudent(
            @RequestParam @NotNull Long id,
            Authentication authentication
    ){
        adviserService.disableBoardStudent(authentication.getName(), id);
        return ResponseEntity.ok(new SuccessResponse(
                200,
                "student disabled from the board"
        ));
    }


    @PostMapping("/enableBoardStudent")
    public ResponseEntity<?> enableBoardStudent(
            @RequestParam @NotNull Long id,
            Authentication authentication
    ){
        adviserService.enableBoardStudent(authentication.getName(), id);
        return ResponseEntity.ok(new SuccessResponse(
                200,
                "The student was disqualified"
        ));
    }



}
