package com.javaguidesl.springboot.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public interface IValidator <T> {


    public Response validateRequest(String reqBody, T t) throws Exception;
}
