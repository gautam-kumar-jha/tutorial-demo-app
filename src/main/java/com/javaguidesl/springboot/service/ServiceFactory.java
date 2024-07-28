package com.javaguidesl.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;

@Component
public class ServiceFactory {

    @Autowired
    private ApplicationContext context;

    public IService getService(String serviceName) {
        return (IService) context.getBean(serviceName);
    }
}
