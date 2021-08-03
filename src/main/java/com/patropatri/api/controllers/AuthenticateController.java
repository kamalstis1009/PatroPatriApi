package com.patropatri.api.controllers;

import com.patropatri.api.models.Authenticate;
import com.patropatri.api.services.AuthenticateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class AuthenticateController {

    @Autowired
    private AuthenticateServiceImpl mService;

    @RequestMapping(value="/authenticate", method= RequestMethod.POST, produces={"application/json"})
    public ResponseEntity<Authenticate> addAuthenticate(@RequestBody Authenticate item) {
        return new ResponseEntity<>(mService.addObject(item), HttpStatus.CREATED);
    }

    //http://localhost:8080/api/authenticate/1
    @RequestMapping(value="/authenticate/{id}", method= RequestMethod.GET, produces={"application/json"})
    public ResponseEntity<Authenticate> getObjectById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(mService.getObjectById(id), HttpStatus.OK);
    }

    //http://localhost:8080/api/authenticate
    @RequestMapping(value="/authenticate", method= RequestMethod.GET, produces={"application/json"})
    public List<Authenticate> getAllObjects() {
        return mService.getAllObjects();
    }

    //http://localhost:8080/api/authenticate/1
    @RequestMapping(value="/authenticate/{id}", method= RequestMethod.PUT, produces={"application/json"})
    public ResponseEntity<Authenticate> updateObject(@RequestBody Authenticate item, @PathVariable("id") UUID id) {
        return new ResponseEntity<>(mService.updateObject(item, id), HttpStatus.OK);
    }

    //http://localhost:8080/api/authenticate/1
    @RequestMapping(value="/authenticate/{id}", method= RequestMethod.DELETE, produces={"application/json"})
    public ResponseEntity<String> deleteObjectById(@PathVariable("id") UUID id) {
        mService.deleteObjectById(id);
        return new ResponseEntity<>("Authenticate deleted successfully!", HttpStatus.OK);
    }

}
