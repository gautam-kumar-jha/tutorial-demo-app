package com.javaguidesl.springboot.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("ValidatorFactory")
public class ValidatorFactory {

    @Autowired
    private ApplicationContext context;

    public Validator getValidator(String name) {
        return (Validator) context.getBean(name);
    }
}
