package com.patropatri.api.models;

import lombok.Data;

@Data
public class MessageDto {

    private String message;
    private String [] imageFiles;
    private Long senderId;
    private Long receiverId;

}
