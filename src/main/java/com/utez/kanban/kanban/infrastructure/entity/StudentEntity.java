package com.utez.kanban.kanban.infrastructure.entity;

import com.utez.kanban.kanban.domain.model.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID;

    private String firstName;
    private String lastName;
    private String gender;
    private String image;



    @OneToOne
    @JoinColumn(name = "userID")
    private UserEntity userEntity;


    @OneToMany(mappedBy = "studentEntity")
    private List<NotificationEntity> notifications;


    public StudentEntity() {
    }

    public StudentEntity(Long studentID, String firstName, String lastName, String gender,
                         String image, UserEntity userEntity, List<NotificationEntity> notifications) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.image = image;
        this.userEntity = userEntity;
        this.notifications = notifications;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<NotificationEntity> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationEntity> notifications) {
        this.notifications = notifications;
    }
}
