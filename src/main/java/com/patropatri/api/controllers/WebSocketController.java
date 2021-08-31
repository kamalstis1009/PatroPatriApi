package com.patropatri.api.controllers;

import com.patropatri.api.models.MessageDto;
import com.patropatri.api.models.socket.SocketRequest;
import com.patropatri.api.models.socket.SocketResponse;
import com.patropatri.api.services.MessageService;
import com.patropatri.api.utils.ConstantKey;
import com.patropatri.api.utils.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WebSocketController {

    @Autowired
    private MessageService mService;

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
    public void chat(MessageDto item) {
        log.info("Receive point-to-point chat message: [" + item.getReceiverId() + " -> " + item.getSenderId() + ", " + item.getMessage() + "]");
        mTemplate.convertAndSendToUser(String.valueOf(item.getReceiverId()), "/msg", item);
        /*MessageDto model = mService.addInbox(item);
        if (model != null) {
            mTemplate.convertAndSendToUser(String.valueOf(item.getReceiverId()), "/msg", item);
        }*/
    }
}
