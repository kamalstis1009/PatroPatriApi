package com.patropatri.api.models;

import java.util.Date;

public interface ICandidate {

    Long getId();
    String getFullName();
    String getGender();
    String getProfession();
    String getDistrict();
    String getSummary();
    Integer getCommentAmount();
    Integer getLikedAmount();
    Integer getViewerAmount();
    String getImageName();
    String getImageCoverName();
    Date getCreatedAt();

}
