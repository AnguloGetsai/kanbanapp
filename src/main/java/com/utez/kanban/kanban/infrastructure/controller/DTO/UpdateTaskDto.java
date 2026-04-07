package com.utez.kanban.kanban.infrastructure.controller.DTO;

import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.List;

public class UpdateTaskDto {
    private String name;
    private String description;
    private String statusKanban;
    private String color;
    private String priority;
    private LocalDate limitDate;

    private String studentIDs;

    private List<MultipartFile> files;

    public UpdateTaskDto(){}

    public UpdateTaskDto(String name, String description, String statusKanban, String color,
                         String priority, LocalDate limitDate, String studentIDs,
                         List<MultipartFile> files) {
        this.name = name;
        this.description = description;
        this.statusKanban = statusKanban;
        this.color = color;
        this.priority = priority;
        this.limitDate = limitDate;
        this.studentIDs = studentIDs;
        this.files = files;
    }

    public String getStudentIDs() {
        return studentIDs;
    }

    public void setStudentIDs(String studentIDs) {
        this.studentIDs = studentIDs;
    }

    public List<Long> getStudentIDsParsed() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(studentIDs, new TypeReference<List<Long>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusKanban() {
        return statusKanban;
    }

    public void setStatusKanban(String statusKanban) {
        this.statusKanban = statusKanban;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }
}
