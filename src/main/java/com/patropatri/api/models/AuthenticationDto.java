package com.patropatri.api.models;

import lombok.Data;

@Data
public class AuthenticationDto {

    private String username;
    private String password;
    private String role;

}
