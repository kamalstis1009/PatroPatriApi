package com.patropatri.api.models;

import lombok.Data;

@Data
public class UserFilter {

    private String gender;
    private String maritalStatus;
    private String religion;
    private String profession;
    private Integer fromYear;
    private Integer toYear;
    private String district;
    private String country;
}
