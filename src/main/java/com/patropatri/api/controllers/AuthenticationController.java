package com.patropatri.api.controllers;

import com.patropatri.api.models.Authentication;
import com.patropatri.api.services.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl mService;

    //======================================================| ADD
    @PostMapping("/authentication")
    public ResponseEntity<Authentication> addAuthenticate(@RequestBody Authentication item) {
        return new ResponseEntity<>(mService.addObject(item), HttpStatus.CREATED);
    }

    //======================================================| GET
    @GetMapping(value="/authentication/{id}", produces = {"application/json"})
    public ResponseEntity<Authentication> getObjectById(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.getObjectById(id), HttpStatus.OK);
    }

    @GetMapping(value="/authentication", produces = {"application/json"})
    public ResponseEntity<List<Authentication>> getAllObjects() {
        return new ResponseEntity<>(mService.getAllObjects(), HttpStatus.OK);
    }

    //======================================================| UPDATE
    @PutMapping("/authentication")
    public ResponseEntity<Authentication> updateObject(@RequestBody Authentication item) {
        return new ResponseEntity<>(mService.updateObject(item), HttpStatus.OK);
    }

    //======================================================| DELETE
    @DeleteMapping("/authentication/{id}")
    public ResponseEntity<Integer> deleteObjectById(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.deleteObjectById(id), HttpStatus.OK);
    }

}
