package com.patropatri.api.controllers;

import com.patropatri.api.models.Authentication;
import com.patropatri.api.models.Family;
import com.patropatri.api.models.User;
import com.patropatri.api.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserServiceImpl mService;

    //======================================================| User
    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User item) {
        return new ResponseEntity<>(mService.addUser(item), HttpStatus.CREATED);
    }

    @GetMapping(value="/user/{id}", produces = {"application/json"})
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User item) {
        return new ResponseEntity<>(mService.updateUser(item), HttpStatus.OK);
    }

    //======================================================| Family
    @PostMapping("/family")
    public ResponseEntity<Family> addFamily(@RequestBody Family item) {
        return new ResponseEntity<>(mService.addFamily(item), HttpStatus.CREATED);
    }

    @GetMapping(value="/family/{id}", produces = {"application/json"})
    public ResponseEntity<Family> getFamilyById(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.getFamilyById(id), HttpStatus.OK);
    }

    @GetMapping(value="/families/{id}", produces = {"application/json"})
    public ResponseEntity<List<Family>> getAllFamiliesByUserId(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.getAllFamilyByUserId(id), HttpStatus.OK);
    }


}
