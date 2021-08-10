package com.patropatri.api.controllers;

import com.patropatri.api.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
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

}
