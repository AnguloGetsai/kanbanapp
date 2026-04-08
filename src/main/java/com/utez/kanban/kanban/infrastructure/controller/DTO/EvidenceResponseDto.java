package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.Evidence;

import java.time.LocalDate;
import java.util.List;

public class EvidenceResponseDto {
    private Long evidenceID;
    private LocalDate uploadDate;
    private String comment;
    private List<EvidenceFileDto> files;

    public static EvidenceResponseDto fromDomain(Evidence evidence){

        List<EvidenceFileDto> files = evidence.getFiles()
                .stream()
                .map(EvidenceFileDto::fromDomain)
                .toList();

        EvidenceResponseDto dto = new EvidenceResponseDto();
        dto.evidenceID = evidence.getEvidenceID();
        dto.uploadDate = evidence.getUploadDate();
        dto.comment = evidence.getComment();
        dto.files = files;

        return dto;
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

    public List<EvidenceFileDto> getFiles() {
        return files;
    }

    public void setFiles(List<EvidenceFileDto> files) {
        this.files = files;
    }
}
