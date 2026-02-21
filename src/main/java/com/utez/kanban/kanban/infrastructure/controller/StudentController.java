package com.utez.kanban.kanban.infrastructure.controller;

import com.utez.kanban.kanban.application.service.StudentService;
import com.utez.kanban.kanban.domain.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.ShortLookupTable;

@RestController
@RequestMapping("/api/kanban")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<?> saveBasicInformation(@RequestBody Student student){
        Student informationSaved = studentService.saveBasicInformation(student);
        return null;
    }

}
