package com.javaguidesl.springboot.service.services;

import com.javaguidesl.springboot.dto.request.registration.UserRegistrationDTO;

import com.javaguidesl.springboot.exception.exceptions.BadRequestException;
import com.javaguidesl.springboot.service.Service;
import com.javaguidesl.springboot.validator.validators.Response;
import com.javaguidesl.springboot.validator.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service("RegisterUser")
public class RegisterUser implements Service {

    @Autowired
    private ValidatorFactory validator;

    @Override
    public String processRequest(String message) throws Exception {

        Response validatorResponse = validator.getValidator("UserRegistration").validateRequest(message, new UserRegistrationDTO());
        if (!validatorResponse.getErrMessage().isEmpty()) {
            throw new BadRequestException(validatorResponse.toJson());
        }

        return null;
    }
}
