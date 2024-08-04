package com.javaguidesl.springboot.controller;

import com.javaguidesl.springboot.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ServerStatusController {

    @Autowired
    private ServiceFactory service;

    @GetMapping(value = "/server/status/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getServerStatus() {
        try {
            String response = service.getService("ClientService").processRequest("");
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>(String.format("{\"errorMessage\": \"%s\"}", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
