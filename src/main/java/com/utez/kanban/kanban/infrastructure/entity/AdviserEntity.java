package com.utez.kanban.kanban.infrastructure.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "adviser")
public class AdviserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adviserID;


    private String firstName;
    private String lastName;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @OneToOne
    @JoinColumn(name = "userID")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "adminID")
    private AdminEntity adminEntity;


    public AdviserEntity(Long adviserID, String firstName, String lastName, byte[] image,
                         UserEntity userEntity, AdminEntity adminEntity) {
        this.adviserID = adviserID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.userEntity = userEntity;
        this.adminEntity = adminEntity;
    }







    public AdviserEntity(){

    }


    public Long getAdviserID() {
        return adviserID;
    }

    public void setAdviserID(Long adviserID) {
        this.adviserID = adviserID;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }
}
