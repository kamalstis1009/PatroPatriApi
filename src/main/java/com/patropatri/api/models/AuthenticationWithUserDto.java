package com.patropatri.api.models;

import lombok.Data;

import java.util.Date;

@Data
public class AuthenticationWithUserDto {

    private String username;
    private String password;
    //private String role;
    private String fullName;
    private Date birthDate;
    private String gender;
    private String religion;

}
