package com.utez.kanban.kanban.infrastructure.controller.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class SubmitEvidenceDTO {
    private Long taskID;
    private String comment;
    private List<MultipartFile> files;

    public Long getTaskID() {
        return taskID;
    }

    public void setTaskID(Long taskID) {
        this.taskID = taskID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
