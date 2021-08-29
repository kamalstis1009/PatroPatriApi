package com.patropatri.api.controllers;

import com.patropatri.api.models.*;
import com.patropatri.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService mService;

    //======================================================| Home
    @GetMapping(value="/users", produces = {"application/json"})
    public ResponseEntity<List<ICandidate>> getAllCandidates() {
        return new ResponseEntity<>(mService.getAllCandidates(), HttpStatus.OK);
    }

    @GetMapping(value="/users/{no}", produces = {"application/json"})
    public ResponseEntity<List<ICandidate>> getAllCandidatesByPaginated(@PathVariable("no") int pageNo, @RequestParam("row") int rowSize) {
        return new ResponseEntity<>(mService.getAllCandidatesByPaginated(pageNo, rowSize), HttpStatus.OK);
    }

    @PostMapping(value="/users-filter")
    public ResponseEntity<List<ICandidate>> getUsersByFilter(@RequestBody UserFilter item, @RequestParam("no") int pageNo, @RequestParam("row") int rowSize) {
        return new ResponseEntity<>(mService.getUsersByFilter(item, pageNo, rowSize), HttpStatus.OK);
    }

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

    @PatchMapping("/user/{id}")
    public ResponseEntity<Boolean> updateUserPost(@PathVariable("id") long id, @RequestParam("isActive") boolean isActive) {
        return new ResponseEntity<>(mService.updateUserPost(id, isActive), HttpStatus.OK);
    }

    //======================================================| Family
    @PostMapping("/family")
    public ResponseEntity<Family> addFamily(@RequestBody Family item) {
        return new ResponseEntity<>(mService.addFamily(item), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/family/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteFamilyById(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.deleteFamilyById(id), HttpStatus.OK);
    }

    @GetMapping(value="/families/{userId}", produces = {"application/json"})
    public ResponseEntity<List<Family>> getAllFamiliesByUserId(@PathVariable("userId") long userId) {
        return new ResponseEntity<>(mService.getAllFamiliesByUserId(userId), HttpStatus.OK);
    }

    //======================================================| Education
    @PostMapping("/education")
    public ResponseEntity<Education> addEducation(@RequestBody Education item) {
        return new ResponseEntity<>(mService.addEducation(item), HttpStatus.CREATED);
    }

    @DeleteMapping(value="/education/{id}", produces = {"application/json"})
    public ResponseEntity<Integer> deleteEducationById(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.deleteEducationById(id), HttpStatus.OK);
    }

    @GetMapping(value="/educations/{userId}", produces = {"application/json"})
    public ResponseEntity<List<Education>> getAllEducationsByUserId(@PathVariable("userId") long userId) {
        return new ResponseEntity<>(mService.getAllEducationsByUserId(userId), HttpStatus.OK);
    }

    //======================================================| Subscription
    @GetMapping("/subscriptions")
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return new ResponseEntity<>(mService.getAllSubscriptions(), HttpStatus.CREATED);
    }


}
