package com.utez.kanban.kanban.infrastructure.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "admin")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminID;
    private String firstName;
    private String lastName;
    private String image;

    @OneToOne
    @JoinColumn(name = "userID")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "adminEntity")
    private List<AdviserEntity> advisers;


    public AdminEntity() {
    }


    public AdminEntity(Long adminID, String firstName, String lastName, String image, UserEntity userEntity) {
        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.userEntity = userEntity;
    }


    public Long getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
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

    public List<AdviserEntity> getAdvisers() {
        return advisers;
    }

    public void setAdvisers(List<AdviserEntity> advisers) {
        this.advisers = advisers;
    }
}
