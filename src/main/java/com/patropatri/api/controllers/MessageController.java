package com.patropatri.api.controllers;

import com.patropatri.api.models.Inbox;
import com.patropatri.api.models.Notification;
import com.patropatri.api.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    @Autowired
    private MessageService mService;

    //======================================================| Notification
    @GetMapping("/notifications/{no}")
    public ResponseEntity<List<Notification>> getAllNotificationsByUserId(@PathVariable("no") int pageNo, @RequestParam("row") int rowSize, @RequestParam("userId") long userId) {
        return new ResponseEntity<>(mService.getAllNotificationsByUserId(pageNo, rowSize, userId), HttpStatus.OK);
    }

    //======================================================| Inbox
    @GetMapping("/inboxes/{no}")
    public ResponseEntity<List<Inbox>> getAllInboxesByUserId(@PathVariable("no") int pageNo, @RequestParam("row") int rowSize, @RequestParam("userId") long userId) {
        return new ResponseEntity<>(mService.getAllInboxesByUserId(pageNo, rowSize, userId), HttpStatus.OK);
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
