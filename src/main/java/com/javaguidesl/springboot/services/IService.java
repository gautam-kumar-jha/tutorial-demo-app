package com.javaguidesl.springboot.services;

public interface IService<T> {
    public String processRequest(T t);
}
