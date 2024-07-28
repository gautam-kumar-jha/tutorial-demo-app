package com.javaguidesl.springboot.controller;

import com.javaguidesl.springboot.exception.BadRequestException;
import com.javaguidesl.springboot.exception.GlobalExceptionHandler;
import com.javaguidesl.springboot.exception.ParseException;
import com.javaguidesl.springboot.service.ServiceFactory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class ServerController {

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

    @PostMapping(value = "/users/register/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@Valid @RequestBody String message) throws BadRequestException {
        try {
            return new ResponseEntity<String>(service.getService("RegisterUser").processRequest(message), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new GlobalExceptionHandler().handleBadRequestException(e);
        } catch (ParseException e) {
            return new GlobalExceptionHandler().handleParseException(e);
         } catch (Exception e){
            return new GlobalExceptionHandler().handleException(e);
        }
    }
}
