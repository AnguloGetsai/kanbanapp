package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.Attachment;
import com.utez.kanban.kanban.domain.model.Board;
import com.utez.kanban.kanban.domain.model.StatusKanban;
import com.utez.kanban.kanban.domain.model.Task;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public class TaskDTO {

    private LocalDate limitDate;
    private LocalDate startDate;
    @NotBlank(message = "The task name is required")
    private String name;
    private String description;
    @NotBlank(message = "Status (ToDo, Doing, Done) is required")
    private String statusKanban;

    private String color;
    private String priority;

    List<Long> studentIDs;

    private List<MultipartFile> files;

    public TaskDTO(LocalDate limitDate, LocalDate startDate,
                   String name, String description, String statusKanban,
                   String color, String priority, List<Long> studentIDs,
                   List<MultipartFile> files) {
        this.limitDate = limitDate;
        this.startDate = startDate;
        this.name = name;
        this.description = description;
        this.statusKanban = statusKanban;
        this.color = color;
        this.priority = priority;
        this.studentIDs = studentIDs;
        this.files = files;
    }

    public static Task toTask(TaskDTO taskDTO){
        return new Task(
                taskDTO.getLimitDate(),
                taskDTO.getStartDate(),
                taskDTO.getName(),
                taskDTO.getDescription(),
                taskDTO.getStatusKanban(),
                taskDTO.getColor(),
                taskDTO.getPriority()
        );
    }


    public static List<Attachment> toAttachment(List<MultipartFile> files){

        if(files == null || files.isEmpty()){
            return List.of();
        }

        return files.stream().map(s -> {
            Attachment attachment = new Attachment();
            attachment.setFileData(getFileByte(s));
            attachment.setFileName(s.getOriginalFilename());
            attachment.setFileType(s.getContentType());
            return attachment;
        }).toList();
    }

    public static byte[] getFileByte(MultipartFile file){
        try{
            return file.getBytes();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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

    public List<Long> getStudentIDs() {
        return studentIDs;
    }

    public void setStudentIDs(List<Long> studentIDs) {
        this.studentIDs = studentIDs;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
