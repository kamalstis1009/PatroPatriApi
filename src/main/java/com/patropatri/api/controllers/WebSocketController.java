package com.patropatri.api.controllers;

import com.patropatri.api.models.MessageDto;
import com.patropatri.api.models.Notification;
import com.patropatri.api.models.socket.SocketRequest;
import com.patropatri.api.models.socket.SocketResponse;
import com.patropatri.api.services.MessageService;
import com.patropatri.api.utils.ConstantKey;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private MessageService mService;

    @Autowired
    private SimpMessagingTemplate mTemplate;

    @MessageMapping("/broadcast")
    @SendTo("/topic")
    public SocketResponse broadcast(SocketRequest message, @Header(value = "authorization") String authorizationToken) {
        val response = new SocketResponse("Token check failed!");
        if (authorizationToken.equals(ConstantKey.TOKEN)) {
            logger.info("Token check success!!!");
            response.setResponse("Welcome, " + message.getName() + "!");
        } else {
            logger.info(response.getResponse());
        }
        return response;
    }

    @MessageMapping("/specific-user")
    public void specificUser(@Payload Notification item) {
        logger.info("Receive point-to-point chat message: [" + item.getReceiverId() + " -> " + item.getSenderId() + ", " + item.getTitle() + "]");
        mTemplate.convertAndSendToUser(String.valueOf(item.getReceiverId()), "/queue/reply", item); //convertAndSendToUser(username, destination, message)
    }

    @MessageMapping("/chat")
    public void chat(MessageDto item) {
        logger.info("Receive point-to-point chat message: [" + item.getReceiverId() + " -> " + item.getSenderId() + ", " + item.getMessage() + "]");
        mTemplate.convertAndSendToUser(String.valueOf(item.getReceiverId()), "/msg", mService.addInbox(item)); //Response Path: /user/placeholder/msg
    }
}
