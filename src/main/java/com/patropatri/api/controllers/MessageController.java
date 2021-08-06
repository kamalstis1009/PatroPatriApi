package com.patropatri.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @RequestMapping({"/hello"})
    public String firstPage() {
        return "Hello World";
    }

}
