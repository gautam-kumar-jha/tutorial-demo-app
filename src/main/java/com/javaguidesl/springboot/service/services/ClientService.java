package com.javaguidesl.springboot.service.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javaguidesl.springboot.dto.response.serverstatus.ServerStatus;
import com.javaguidesl.springboot.service.Service;

@org.springframework.stereotype.Service("ClientService")
public class ClientService implements Service {
    @Override
    public String processRequest(String message) {
        try {
            ServerStatus response = new ServerStatus();
            response.setStatus("running");
            return response.toJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
