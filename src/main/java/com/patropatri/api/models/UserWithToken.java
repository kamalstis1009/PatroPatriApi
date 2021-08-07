package com.patropatri.api.models;

import lombok.Data;

@Data
public class UserWithToken {

    private String token;
    private Authentication auth;
    private User user;

}
