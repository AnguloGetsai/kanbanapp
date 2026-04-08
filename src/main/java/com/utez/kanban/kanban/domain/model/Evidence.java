package com.utez.kanban.kanban.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Evidence {
    private Long evidenceID;
    private LocalDate uploadDate;
    private String comment;

    private StudentTask studentTask;
    private List<EvidenceFile> files;

    public Evidence(){}

    public Evidence(Long evidenceID, LocalDate uploadDate, String comment,
                    StudentTask studentTask, List<EvidenceFile> files) {
        this.evidenceID = evidenceID;
        this.uploadDate = uploadDate;
        this.comment = comment;
        this.studentTask = studentTask;
        this.files = files;
    }

    public Long getEvidenceID() {
        return evidenceID;
    }

    public void setEvidenceID(Long evidenceID) {
        this.evidenceID = evidenceID;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public StudentTask getStudentTask() {
        return studentTask;
    }

    public void setStudentTask(StudentTask studentTask) {
        this.studentTask = studentTask;
    }

    public List<EvidenceFile> getFiles() {
        return files;
    }

    public void setFiles(List<EvidenceFile> files) {
        this.files = files;
    }
}
