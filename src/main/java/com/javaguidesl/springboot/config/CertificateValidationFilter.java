package com.javaguidesl.springboot.config;

import com.javaguidesl.springboot.exception.exceptions.CertificateSubjectInvalidException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class CertificateValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        X509Certificate[] certificates = (X509Certificate[]) request.getAttribute("jakarta.servlet.request.X509Certificate");
        if (certificates != null && certificates.length > 0) {
            X509Certificate certificate = certificates[0];
            String subject;
            try {
                certificate.checkValidity();
                subject = certificate.getSubjectX500Principal().getName();
                if (!validateCertificateSubject(subject))
                    throw new CertificateSubjectInvalidException("certificate is not valid");
            } catch (CertificateExpiredException | CertificateNotYetValidException | CertificateSubjectInvalidException ex) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                return;
            }
            Authentication authentication = new CertificateAuthenticationToken(subject, certificate);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }

    private boolean validateCertificateSubject(String subject) {
        String[] subjectLine = {"CN=gautam, OU=tutorial", "CN=gautam, OU=tutorial, O=tutorial, L=Noida, ST=UP, C=TT", "CN=client"};
        return Arrays.asList(subjectLine).contains(subject);
    }

}

