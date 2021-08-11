package com.patropatri.api.models;

import java.util.Date;

public interface Candidate {

    Long getId();
    String getFullName();
    String getGender();
    String getProfession();
    String getDistrict();
    String getSummary();
    Integer getCommentAmount();
    Integer getLikedAmount();
    String getImageName();
    Date getCreatedAt();

}
