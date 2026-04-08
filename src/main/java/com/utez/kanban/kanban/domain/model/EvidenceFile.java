package com.utez.kanban.kanban.domain.model;

public class EvidenceFile {
    private Long fileID;
    private String fileName;
    private String fileType;
    private byte[] fileData;

    private Evidence evidence;



    public EvidenceFile(){}

    public EvidenceFile(Long fileID, String fileName, String fileType, byte[] fileData, Evidence evidence) {
        this.fileID = fileID;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
        this.evidence = evidence;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

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
}
