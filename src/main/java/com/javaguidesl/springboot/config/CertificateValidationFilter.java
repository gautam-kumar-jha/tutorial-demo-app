package com.javaguidesl.springboot.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.cert.X509Certificate;

public class CertificateValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        X509Certificate[] certificates = (X509Certificate[]) request.getAttribute("jakarta.servlet.request.X509Certificate");
        if (certificates != null && certificates.length > 0) {
            X509Certificate certificate = certificates[0];
            String subject = certificate.getSubjectX500Principal().getName();

            if (validateCertificateSubject(subject)) {
                Authentication authentication = new CertificateAuthenticationToken(subject, certificate);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid certificate subject");
                return;
            }
            filterChain.doFilter(request, response);
        }
    }

    private boolean validateCertificateSubject(String subject) {
        return subject.contains("CN=client");
    }

}

