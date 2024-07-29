package com.javaguidesl.springboot.validator;

import com.javaguidesl.springboot.validator.validators.Response;
import org.springframework.stereotype.Component;

@Component
public interface Validator<T> {


    public Response validateRequest(String reqBody, T t) throws Exception;
}
