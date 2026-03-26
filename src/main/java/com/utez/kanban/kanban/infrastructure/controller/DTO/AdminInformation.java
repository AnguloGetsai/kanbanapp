package com.utez.kanban.kanban.infrastructure.controller.DTO;

import com.utez.kanban.kanban.domain.model.Admin;
import jakarta.validation.constraints.NotBlank;


import java.util.Base64;

public class AdminInformation {

    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;

    private String email;


    private String logo;



    public AdminInformation(){

    }


    public AdminInformation(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public AdminInformation(String firstName, String lastName, String email, String logo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        this.logo = logo;
    }


    public static AdminInformation toAdminInformation(Admin admin){
        String logoBase64 = "";
        if(admin.getImage() != null){
            logoBase64 = Base64.getEncoder().encodeToString(admin.getImage());
        }
        System.out.println("Este es el lgogo bro  "+logoBase64);

        return new AdminInformation(
                admin.getFirstName(),
                admin.getLastName(),
                admin.getUser().getEmail(),
                logoBase64
        );
    }


    public static Admin toAdmin(AdminInformation adminInformation){

        return new Admin(
                adminInformation.getFirstName(),
                adminInformation.getLastName()
        );
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
