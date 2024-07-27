package com.javaguidesl.springboot.validator;

public interface IValidator <T> {
    public Response validateRequest(String reqBody, T t) throws Exception;
}
