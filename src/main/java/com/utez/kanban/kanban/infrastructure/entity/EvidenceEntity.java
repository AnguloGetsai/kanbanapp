package com.utez.kanban.kanban.infrastructure.entity;

import jakarta.persistence.*;

import javax.print.attribute.standard.MediaSize;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "evidence")
public class EvidenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evidenceID;

    @Column(name = "upload_date", nullable = false)
    private LocalDate uploadDate;

    private String comment;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "studentID", referencedColumnName = "studentID", nullable = false),
            @JoinColumn(name = "taskID", referencedColumnName = "taskID", nullable = false)
    })
    private StudentTaskEntity studentTaskEntity;

    @OneToMany(mappedBy = "evidenceEntity", cascade = CascadeType.ALL)
    private List<EvidenceFileEntity> evidenceFiles;

    public EvidenceEntity() {}


    public EvidenceEntity(Long evidenceID, LocalDate uploadDate,
                          String comment, StudentTaskEntity
                                  studentTaskEntity,
                          List<EvidenceFileEntity> evidenceFiles) {
        this.evidenceID = evidenceID;
        this.uploadDate = uploadDate;
        this.comment = comment;
        this.studentTaskEntity = studentTaskEntity;
        this.evidenceFiles = evidenceFiles;
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

    public StudentTaskEntity getStudentTaskEntity() {
        return studentTaskEntity;
    }

    public void setStudentTaskEntity(StudentTaskEntity studentTaskEntity) {
        this.studentTaskEntity = studentTaskEntity;
    }

    public List<EvidenceFileEntity> getEvidenceFiles() {
        return evidenceFiles;
    }

    public void setEvidenceFiles(List<EvidenceFileEntity> evidenceFiles) {
        this.evidenceFiles = evidenceFiles;
    }
}
