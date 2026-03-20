package com.utez.kanban.kanban.domain.model;

public class Attachment {
    private Long attachmentID;
    private String fileName;
    private String fileType;
    private byte[] fileData;

    private Task task;


    public Attachment(){}

    public Attachment(Long attachmentID, String fileName,
                      String fileType, byte[] fileData,
                      Task task) {
        this.attachmentID = attachmentID;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
        this.task = task;
    }

    public Long getAttachmentID() {
        return attachmentID;
    }

    public void setAttachmentID(Long attachmentID) {
        this.attachmentID = attachmentID;
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
