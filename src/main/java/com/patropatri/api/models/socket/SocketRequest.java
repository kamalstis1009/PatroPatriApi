package com.patropatri.api.models.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Message sent to server by browser
 *
 * Encapsulate the message sent by the browser to the server
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocketRequest {
    private String name;
}
