package com.patropatri.api.models;

import java.util.Date;

public interface IInbox {

    Long getId();
    String getMessage();
    Long getSenderId();
    String getSenderName();
    String getSenderImage();
    Boolean getIsEnabled();
    Date getCreatedAt();
}
