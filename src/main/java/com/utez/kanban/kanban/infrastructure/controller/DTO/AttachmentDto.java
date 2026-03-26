package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.Attachment;
import com.utez.kanban.kanban.infrastructure.entity.AttachmentEntity;

import java.util.Base64;

public class AttachmentDto {

    private Long id;
    private String fileName;
    private String fileType;
    private String file; // base64

    public AttachmentDto(Long id, String fileName, String fileType, String file) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public static AttachmentDto fromEntity(AttachmentEntity a){
        String fileB64 = "";

        if(a.getFileData() != null){
            fileB64 = Base64.getEncoder().encodeToString(a.getFileData());
        }

        return new AttachmentDto(
                a.getAttachmentID(),
                a.getFileName(),
                a.getFileType(),
                fileB64
        );



    }



    public static AttachmentDto fromDomain(Attachment a){
        String fileB64 = "";

        if(a.getFileData() != null){
            fileB64 = Base64.getEncoder().encodeToString(a.getFileData());
        }

        return new AttachmentDto(
                a.getAttachmentID(),
                a.getFileName(),
                a.getFileType(),
                fileB64
        );
    }

}
