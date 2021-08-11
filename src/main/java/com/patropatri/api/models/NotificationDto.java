package com.patropatri.api.models;

import java.util.Date;

public interface NotificationDto {

    Long getId();
    String getTitle();
    String getType();
    Long getSenderId();
    String getSenderName();
    String getSenderImage();
    Boolean getIsEnabled();
    Date getCreatedAt();
}
