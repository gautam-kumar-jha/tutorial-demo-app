package com.javaguidesl.springboot.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javaguidesl.springboot.dto.request.serverstatus.ServerStatusRequest;
import com.javaguidesl.springboot.dto.response.serverstatus.ServerStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ClientService implements IService<ServerStatusRequest> {
    @Override
    public String processRequest(ServerStatusRequest reqBody) {
        try {
            ServerStatus response = new ServerStatus();
            response.setServerName(reqBody.getServerName());
            response.setStatus("running");
            return response.toJson();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
