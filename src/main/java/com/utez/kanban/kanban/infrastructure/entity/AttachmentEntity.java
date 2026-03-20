package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attachment")
public class AttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentID;
    private String fileName;
    private String fileType;
    private byte[] fileData;

    @ManyToOne
    @JoinColumn(name = "taskID")
    private TaskEntity taskEntity;

    public AttachmentEntity(Long attachmentID, String fileName,
                            String fileType, byte[] fileData,
                            TaskEntity taskEntity) {
        this.attachmentID = attachmentID;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
        this.taskEntity = taskEntity;
    }

    public AttachmentEntity(){}

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

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }
}
