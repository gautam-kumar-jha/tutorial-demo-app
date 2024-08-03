package com.javaguidesl.springboot.validator.validators;

import com.javaguidesl.springboot.dto.request.registration.UserRegistrationDTO;
import com.javaguidesl.springboot.exception.exceptions.ParseException;
import com.javaguidesl.springboot.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Component("UserRegistration")
public class UserRegistration implements Validator<UserRegistrationDTO> {

    @Autowired
    private org.springframework.validation.Validator validator;

    @Override
    public Response validateRequest(String readMessage, UserRegistrationDTO reqBody) throws Exception {
        try {
            reqBody = UserRegistrationDTO.fromJson(readMessage);
            Response response = new Response("");
            BindingResult bindingResult = new BeanPropertyBindingResult(reqBody, "reqBody");
            validator.validate(reqBody, bindingResult);
            if (bindingResult.hasErrors()) {
                List<String> errors = new ArrayList<>();
                bindingResult.getAllErrors().forEach(error -> {
                    String fieldName = ((org.springframework.validation.FieldError) error).getField();
                    errors.add(fieldName);
                });
                String errorMessage = String.format("%s is missing from payload", errors.toString().substring(1, errors.toString().length() - 1));
                response.setErrMessage(errorMessage);
            }
            return response;
        } catch (Exception e) {
            throw new ParseException("error in request parsing");
        }
    }
}