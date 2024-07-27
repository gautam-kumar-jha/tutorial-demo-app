package com.javaguidesl.springboot.validator;

import com.javaguidesl.springboot.dto.request.serverstatus.ServerStatusRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidateServerStatusRequest implements IValidator<ServerStatusRequest>{

    @Autowired
    private Validator validator;

    @Override
    public Response validateRequest(String readMessage, ServerStatusRequest reqBody) throws Exception {
        try {
            reqBody = ServerStatusRequest.fromJson(readMessage);
            Response response = new Response("valid", "", true);
            BindingResult bindingResult = new BeanPropertyBindingResult(reqBody, "reqBody");
            validator.validate(reqBody, bindingResult);
            if (bindingResult.hasErrors()) {
                List<String> errors = new ArrayList<>();
                bindingResult.getAllErrors().forEach(error -> {
                    String fieldName = ((org.springframework.validation.FieldError) error).getField();
                    errors.add(fieldName);
                });
                String errorMessage = String.format("%s is missing from payload", errors.toString().substring(1, errors.toString().length() -1));
                response.setErrMessage(errorMessage);
                response.setStatus(false);
                response.setMessage("invalid");
            } else {
                response.setObj(reqBody);
            }
            return response;
        }catch(Exception e) {
            throw new ParseException("error in request parsing", 500);
        }
    }
}
