package com.patropatri.api.models;

import java.util.Date;

public interface LikedDto {

    Long geId();
    String getFullName();
    String getImageName();
    Long getUserId();
    Date getCreatedAt();

}
