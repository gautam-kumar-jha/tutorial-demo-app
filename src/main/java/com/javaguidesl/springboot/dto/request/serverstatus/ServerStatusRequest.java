package com.javaguidesl.springboot.dto.request.serverstatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotEmpty;

public class ServerStatusRequest {

    @NotEmpty(message = "serverName")
    private String serverName;

    public ServerStatusRequest() {}

    public String getServerName() {return serverName;}

    public static ServerStatusRequest fromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, ServerStatusRequest.class);
    }
}
