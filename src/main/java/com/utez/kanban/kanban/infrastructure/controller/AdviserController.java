package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.AdviserService;
import com.utez.kanban.kanban.application.service.StudentService;
import com.utez.kanban.kanban.domain.model.Adviser;
import com.utez.kanban.kanban.domain.model.Student;
import com.utez.kanban.kanban.domain.model.Task;
import com.utez.kanban.kanban.domain.model.exeption.user.UserNotFoundException;
import com.utez.kanban.kanban.infrastructure.controller.DTO.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/adviser")
@Validated
public class AdviserController {
    private final AdviserService adviserService;

    public AdviserController(AdviserService adviserService){
        this.adviserService = adviserService;
    }
    @PostMapping("/registerStudent")
    public ResponseEntity<?> registerStudent(@RequestBody @Valid StudentRegister studentRegister, Authentication authentication){
        adviserService.registerStudent(
                authentication.getName(),
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
    // solo manda la imagen
    @PostMapping("/uploadLogo")
    public ResponseEntity<?> uploadLogo(
            @RequestParam("file") MultipartFile file,
            Authentication authentication
            ){
        try {
            adviserService.uploadLogo(authentication.getName(), file.getBytes());
            return ResponseEntity.ok(new SuccessResponse(200, "logo added"));
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    // solo manda firstName and lastName
    @PostMapping("/updateAdivserInformation")
    public ResponseEntity<?> updateAdviserInformation(
            @RequestBody @Valid AdviserInformation adviserInformation,
            Authentication authentication
            ){
        adviserService.updateAdviserInformation(authentication.getName(), AdviserInformation.toAdviser(adviserInformation));
        return ResponseEntity.ok("updated adviser");
    }



    @GetMapping("/getAdviserInformation")
    public ResponseEntity<?> getAdviserInformation(Authentication authentication){
        Adviser adviser = adviserService.getAdviserInformation(authentication.getName())
                .orElseThrow(() -> new UserNotFoundException("USER NOT FOUND"));
        return ResponseEntity.ok(AdviserInformation.toAdviserInformation(adviser));
    }

    @PostMapping(value = "/createTask",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createTask(
            @ModelAttribute @Valid TaskDTO taskDTO,
            Authentication authentication
    ){
        adviserService.createTask(
                taskDTO.getStudentIDs(),
                TaskDTO.toTask(taskDTO),
                authentication.getName(),
                TaskDTO.toAttachment(taskDTO.getFiles())
        );

        return ResponseEntity.ok(new SuccessResponse(201, "Added task successful"));
    }


    @GetMapping("/getTasks")
    public ResponseEntity<?> getAllTasks(Authentication authentication){

        List<TaskSimpleDto> response = adviserService
                .getAllTasks(authentication.getName())
                .stream()
                .map(TaskSimpleDto::fromDomain)
                .toList();

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<?> deleteTask(
            @PathVariable Long id,
            Authentication authentication
    ){
        adviserService.deleteTask(id, authentication.getName());
        return ResponseEntity.ok(new SuccessResponse(200, "Task deleted"));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> updateTask(
            @PathVariable Long id,
            @RequestBody UpdateTaskDto dto,
            Authentication authentication
    ){

        Task task = new Task();
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setStatusKanban(dto.getStatusKanban());
        task.setColor(dto.getColor());
        task.setPriority(dto.getPriority());
        task.setLimitDate(dto.getLimitDate());

        adviserService.updateTask(id, authentication.getName(), task);

        return ResponseEntity.ok(new SuccessResponse(200, "Task updated"));
    }


    @PatchMapping("/task/{id}/status")
    public ResponseEntity<?> updateTaskStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusDto dto,
            Authentication authentication
    ){

        adviserService.updateTaskStatus(
                id,
                authentication.getName(),
                dto.getStatus()
        );

        return ResponseEntity.ok(new SuccessResponse(200, "Status updated"));
    }

}
