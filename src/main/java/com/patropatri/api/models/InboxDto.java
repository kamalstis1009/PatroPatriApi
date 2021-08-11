package com.patropatri.api.models;

import lombok.Data;

@Data
public class InboxDto {

    private String message;
    private String [] imageFiles;
    private Long senderId;
    private Long receiverId;

}
