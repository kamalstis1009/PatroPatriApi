package com.patropatri.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AuthenticationWithUserDto {

    private String username;
    private String password;
    //private String role;
    private String fullName;
    @JsonFormat(pattern="dd-MM-yyyy") //yyyy-MM-dd'T'HH:mm:ss
    private Date birthDate;
    private String gender;
    private String religion;
    private String country;

}
