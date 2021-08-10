package com.patropatri.api.controllers;

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

    //======================================================| GET
    /*@GetMapping(value="/galleries/{id}", produces = {"application/json"})
    public ResponseEntity<List<Gallery>> getAllGalleriesByUserId(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.getAllGalleriesByUserId(id), HttpStatus.OK);
    }

    //======================================================| DELETE
    @DeleteMapping("/gallery/{id}")
    public ResponseEntity<Integer> deleteGalleryById(@PathVariable("id") long id, @RequestParam("fileName") String fileName) {
        return new ResponseEntity<>(mService.deleteGalleryById(id, fileName), HttpStatus.OK);
    }*/

    @GetMapping("/paginate/{pageNo}")
    public ResponseEntity<List<Notification>> getAllPaginatedNotificationsByUserId(@PathVariable("pageNo") int pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
        int pageSize = 15;
        Page<Notification> page = mService.getAllPaginatedNotificationsByUserId(pageNo, pageSize, sortField, sortDir);
        List<Notification> list = page.getContent();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/notifications/{pageNo}")
    public ResponseEntity<List<Notification>> findByUserId(@PathVariable("no") int pageNo, @RequestParam("row") int rowSize, @RequestParam("userId") long userId) {
        return new ResponseEntity<>(mService.findByUserId(pageNo, rowSize, userId), HttpStatus.OK);
    }

}
