package com.patropatri.api.controllers;

import com.patropatri.api.models.*;
import com.patropatri.api.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService mService;

    @Autowired
    private SimpMessagingTemplate mTemplate;

    //======================================================| Notification
    @GetMapping("/notifications/{no}")
    public ResponseEntity<List<INotification>> getAllNotificationsByUserId(@PathVariable("no") int pageNo, @RequestParam("row") int rowSize, @RequestParam("userId") long userId) {
        return new ResponseEntity<>(mService.getAllNotificationsByUserId(pageNo, rowSize, userId), HttpStatus.OK);
    }

    @PostMapping("/notification")
    public ResponseEntity<Notification> addNotification(@RequestBody Notification item) {
        return new ResponseEntity<>(mService.addNotification(item), HttpStatus.CREATED);
    }

    @DeleteMapping("/notification/{id}")
    public ResponseEntity<Integer> deleteNotificationById(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.deleteNotificationById(id), HttpStatus.OK);
    }

    //======================================================| Inbox
    @GetMapping("/inboxes/{no}")
    public ResponseEntity<List<IInbox>> getAllInboxesByUserId(@PathVariable("no") int pageNo, @RequestParam("row") int rowSize, @RequestParam("userId") long userId) {
        return new ResponseEntity<>(mService.getAllInboxesByUserId(pageNo, rowSize, userId), HttpStatus.OK);
    }

    @PostMapping("/inbox")
    public ResponseEntity<MessageDto> addInbox(@RequestBody MessageDto item) {
        return new ResponseEntity<>(mService.addInbox(item), HttpStatus.CREATED);
    }

    @DeleteMapping("/inbox/{id}")
    public ResponseEntity<Integer> deleteInboxById(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.deleteInboxById(id), HttpStatus.OK);
    }

    //======================================================| Chat/Message
    @GetMapping("/chats/{no}")
    public ResponseEntity<List<Message>> getAllMessages(@PathVariable("no") int pageNo, @RequestParam("row") int rowSize, @RequestParam("userId") long userId) {
        return new ResponseEntity<>(mService.getAllMessages(pageNo, rowSize, userId), HttpStatus.OK);
    }

    @MessageMapping("/chat")
    public void chat(MessageDto item) {
        logger.info("Receive point-to-point chat message: [" + item.getReceiverId() + " -> " + item.getSenderId() + ", " + item.getMessage() + "]");
        MessageDto model = mService.addInbox(item);
        if (model != null) {
            mTemplate.convertAndSendToUser(String.valueOf(item.getSenderId()), "/msg", item);
        }
    }

    //======================================================| Comment
    @GetMapping("/comments/{userId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsByUserId(@PathVariable("userId") int userId) {
        return new ResponseEntity<>(mService.getAllCommentsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<Comment> addComment(@RequestBody Comment item) {
        return new ResponseEntity<>(mService.addComment(item), HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Integer> deleteCommentById(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.deleteCommentById(id), HttpStatus.OK);
    }

    //======================================================| Liked
    @GetMapping("/like/{userId}")
    public ResponseEntity<Liked> getLikedByUserId(@PathVariable("userId") int userId) {
        return new ResponseEntity<>(mService.getLikedByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/likes/{userId}")
    public ResponseEntity<List<LikedDto>> getAllLikesByUserId(@PathVariable("userId") int userId) {
        return new ResponseEntity<>(mService.getAllLikesByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/like")
    public ResponseEntity<Liked> addLiked(@RequestBody Liked item) {
        return new ResponseEntity<>(mService.addLiked(item), HttpStatus.CREATED);
    }

    //======================================================| DEMO
    @GetMapping("/paginate/{pageNo}")
    public ResponseEntity<List<Notification>> findByUserId(@PathVariable("pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
        int pageSize = 15;
        Page<Notification> page = mService.findByUserId(pageNo, pageSize, sortField, sortDir);
        List<Notification> list = page.getContent();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
