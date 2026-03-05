package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDate;
@Entity
@Table(name = "evidence")
public class EvidenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evidenceID;

    @Column(name = "file_url")
    private String fileUrl;
    private String type;

    @Column(name = "upload_date")
    private LocalDate uploadDate;
    private String comment;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "studentID", referencedColumnName = "studentID"),
            @JoinColumn(name = "taskID", referencedColumnName = "taskID")
    })
    private StudentTaskEntity studentTaskEntity;


    public EvidenceEntity(Long evidenceID, String fileUrl, String type, LocalDate uploadDate, String comment, StudentTaskEntity studentTaskEntity) {
        this.evidenceID = evidenceID;
        this.fileUrl = fileUrl;
        this.type = type;
        this.uploadDate = uploadDate;
        this.comment = comment;
        this.studentTaskEntity = studentTaskEntity;
    }

    public EvidenceEntity() {
    }
}
