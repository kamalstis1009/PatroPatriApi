package com.patropatri.api.models;

import lombok.Data;

import java.util.Date;

@Data
public class Candidate {

    private long id;
    private String fullName;
    private String gender;
    private String profession;
    private String district;
    private String summary;
    private int commentAmount;
    private int likedAmount;
    private Date createdAt;

}
