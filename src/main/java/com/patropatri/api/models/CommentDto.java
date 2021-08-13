package com.patropatri.api.models;

import java.util.Date;

public interface CommentDto {

    Long getId();
    String getMessage();
    String getFullName();
    String getImageName();
    Long getUserId();
    Date getCreatedAt();

}
