package com.javaguidesl.springboot.controller;

import com.javaguidesl.springboot.dto.request.serverstatus.ServerStatusRequest;
import com.javaguidesl.springboot.validator.Response;
import com.javaguidesl.springboot.services.IService;
import com.javaguidesl.springboot.validator.IValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ServerController {

    @Autowired
    private IService service;

    @Autowired
    private IValidator validator;

    @RequestMapping(value = "/server/status", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> getServerStatus(@Valid @RequestBody String message) {
        try {
            Response validatorResponse = validator.validateRequest(message,new ServerStatusRequest());
            if (!validatorResponse.isStatus()) {
                return new ResponseEntity<String>(validatorResponse.toJson(), HttpStatus.BAD_REQUEST);
            }
            ServerStatusRequest jsonRequest = (ServerStatusRequest) validatorResponse.getObj();
            String response = service.processRequest(jsonRequest);
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>(String.format("{\"errorMessage\": \"%s\"}", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
