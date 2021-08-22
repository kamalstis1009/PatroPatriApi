package com.patropatri.api.models.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Message sent to client
 *
 * Encapsulate the message sent to the client
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocketResponse {
    private String response;
}
