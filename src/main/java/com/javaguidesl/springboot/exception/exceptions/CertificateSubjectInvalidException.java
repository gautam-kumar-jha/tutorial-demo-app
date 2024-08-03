package com.javaguidesl.springboot.exception.exceptions;

public class CertificateSubjectInvalidException extends RuntimeException{

    public CertificateSubjectInvalidException(String message) {
        super(message);
    }
    public CertificateSubjectInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}