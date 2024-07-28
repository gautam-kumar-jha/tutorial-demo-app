package com.javaguidesl.springboot.services;

import com.javaguidesl.springboot.dto.request.registration.UserRegistrationDTO;

import com.javaguidesl.springboot.exception.BadRequestException;
import com.javaguidesl.springboot.exception.ParseException;
import com.javaguidesl.springboot.validator.Response;
import com.javaguidesl.springboot.validator.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RegisterUser")
public class RegisterUser implements IService{

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
