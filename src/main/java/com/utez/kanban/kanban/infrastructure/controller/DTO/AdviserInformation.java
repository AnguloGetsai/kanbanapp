package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.Adviser;
import jakarta.validation.constraints.NotBlank;

import java.util.Base64;

public class AdviserInformation {
    private Long id;
    private String email;
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    private boolean status;
    private String logo;

    public AdviserInformation(){

    }

    public static Adviser toAdviser(AdviserInformation adviserInformation){
        return new Adviser(
                adviserInformation.getFirstName(),
                adviserInformation.getLastName()
        );
    }

    public static AdviserInformation toAdviserInformation(Adviser adviser){
        String logoB6 = "";
        if(adviser.getImage() != null){
            logoB6 = Base64.getEncoder().encodeToString(adviser.getImage());
        }
        AdviserInformation adviserInformation = new AdviserInformation(
                adviser.getAdviserID(),
                adviser.getFirstName(),
                adviser.getLastName(),
                adviser.getUser().getEmail()
        );
        adviserInformation.setLogo(logoB6);
        return  adviserInformation;
    }

    public AdviserInformation(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AdviserInformation(Long id, String email, String firstName, String lastName, boolean status) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public AdviserInformation(Long id,String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
