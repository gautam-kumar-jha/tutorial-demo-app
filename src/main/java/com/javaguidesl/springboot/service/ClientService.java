package com.javaguidesl.springboot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javaguidesl.springboot.dto.request.serverstatus.ServerStatusRequest;
import com.javaguidesl.springboot.dto.response.serverstatus.ServerStatus;
import org.springframework.stereotype.Service;

@Service("ClientService")
public class ClientService implements IService {
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
