package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.EvidenceFile;

import java.util.Base64;

public class EvidenceFileDto {
    private String fileName;
    private String fileType;
    private String file; // base64

    public static EvidenceFileDto fromDomain(EvidenceFile ef){
        EvidenceFileDto dto = new EvidenceFileDto();
        dto.fileName = ef.getFileName();
        dto.fileType = ef.getFileType();
        dto.file = Base64.getEncoder().encodeToString(ef.getFileData());
        return dto;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
