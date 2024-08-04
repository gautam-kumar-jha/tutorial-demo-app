package com.javaguidesl.springboot.controller;

import com.javaguidesl.springboot.exception.GlobalExceptionHandler;
import com.javaguidesl.springboot.exception.exceptions.BadRequestException;
import com.javaguidesl.springboot.exception.exceptions.ParseException;
import com.javaguidesl.springboot.service.ServiceFactory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RegisterUserController {

    @Autowired
    private ServiceFactory service;

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
