package com.javaguidesl.springboot.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import java.security.cert.X509Certificate;
import java.util.Collections;

public class CertificateAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;
    private final X509Certificate credentials;

    public CertificateAuthenticationToken(String principal, X509Certificate credentials) {
        super(Collections.emptyList());
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(true);
    }

    @Override
    public String getPrincipal() {
        return principal;
    }

    @Override
    public X509Certificate getCredentials() {
        return credentials;
    }
}
