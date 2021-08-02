package com.patropatri.api.controllers;

import com.patropatri.api.models.Authenticate;
import com.patropatri.api.services.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authenticate")
public class AuthenticateController {

    @Autowired
    private AuthenticateService mService;

    @RequestMapping(value="/add", method= RequestMethod.POST, produces={"application/json"})
    public ResponseEntity<Authenticate> addAuthenticate(@RequestBody Authenticate item) {
        return new ResponseEntity<Authenticate>(mService.addObject(item), HttpStatus.CREATED);
    }

}
