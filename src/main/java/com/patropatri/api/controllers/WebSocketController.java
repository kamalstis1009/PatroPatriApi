package com.patropatri.api.controllers;

import com.patropatri.api.models.socket.ChatMessage;
import com.patropatri.api.models.socket.SocketRequest;
import com.patropatri.api.models.socket.SocketResponse;
import com.patropatri.api.utils.ConstantKey;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate mTemplate;

    @MessageMapping("/broadcast")
    @SendTo("/b")
    public SocketResponse broadcast(SocketRequest message, @Header(value = "authorization") String authorizationToken) {
        val response = new SocketResponse("Token check failed!");
        if (authorizationToken.equals(ConstantKey.TOKEN)) {
            log.info("Token check success!!!");
            response.setResponse("Welcome, " + message.getName() + "!");
        } else {
            log.info(response.getResponse());
        }
        return response;
    }

    @MessageMapping("/group/{groupID}")
    public void group(@DestinationVariable int groupID, SocketRequest message) {
        log.info("Receive group message: [" + groupID + " -> " + message.getName() + "]");
        SocketResponse response = new SocketResponse("Welcome to group " + groupID + ", " + message.getName() + "!");
        mTemplate.convertAndSend("/g/" + groupID, response);
    }

    @MessageMapping("/chat")
    public void chat(ChatMessage chatMessage) {
        log.info("Receive point-to-point chat message: [" + chatMessage.getFromUserID() + " -> " + chatMessage.getUserID() + ", " + chatMessage.getMessage() + "]");
        SocketResponse response = new SocketResponse("Receive message from user " + chatMessage.getFromUserID() + ": " + chatMessage.getMessage());
        mTemplate.convertAndSendToUser(String.valueOf(chatMessage.getUserID()), "/msg", response);
    }
}
