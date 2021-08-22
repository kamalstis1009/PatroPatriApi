package com.patropatri.api.models.socket;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private int userID;
    private int fromUserID;
    private String message;
}
