package com.patropatri.api.controllers;

import com.patropatri.api.models.Authenticate;
import com.patropatri.api.services.AuthenticateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthenticateController {

    @Autowired
    private AuthenticateServiceImpl mService;

    @PostMapping("/authenticate")
    public ResponseEntity<Authenticate> addAuthenticate(@RequestBody Authenticate item) {
        return new ResponseEntity<>(mService.addObject(item), HttpStatus.CREATED);
    }

    @GetMapping(value="/authenticate/{id}", produces= {"application/json"})
    public ResponseEntity<Authenticate> getObjectById(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.getObjectById(id), HttpStatus.OK);
    }

    @GetMapping(value="/authenticates", produces= {"application/json"})
    public List<Authenticate> getAllObjects() {
        return mService.getAllObjects();
    }

    @PutMapping("/authenticate")
    public ResponseEntity<Authenticate> updateObject(@RequestBody Authenticate item) {
        return new ResponseEntity<>(mService.updateObject(item), HttpStatus.OK);
    }

    @DeleteMapping("/authenticate/{id}")
    public ResponseEntity<Integer> deleteObjectById(@PathVariable("id") long id) {
        return new ResponseEntity<>(mService.deleteObjectById(id), HttpStatus.OK);
    }

}
