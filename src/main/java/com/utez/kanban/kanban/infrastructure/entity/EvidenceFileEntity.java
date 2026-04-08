package com.utez.kanban.kanban.infrastructure.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "evidence_file")
public class EvidenceFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileID;

    private String fileName;

    private String fileType;
    @Lob
    private byte[] fileData;
    @ManyToOne
    @JoinColumn(name = "evidenceID")
    private EvidenceEntity evidenceEntity;


    public EvidenceFileEntity(Long fileID, String fileName, String fileType, byte[] fileData, EvidenceEntity evidenceEntity) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
        this.evidenceEntity = evidenceEntity;
    }

    public EvidenceFileEntity(){}


    public Long getFileID() {
        return fileID;
    }

    public void setFileID(Long fileID) {
        this.fileID = fileID;
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

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public EvidenceEntity getEvidenceEntity() {
        return evidenceEntity;
    }

    public void setEvidenceEntity(EvidenceEntity evidenceEntity) {
        this.evidenceEntity = evidenceEntity;
    }
}