package com.javaguidesl.springboot.config;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Set;

public class CertificateAuthenticationProvider extends UsernamePasswordAuthenticationFilter {

    private final Set<String> allowedSubjectNames;

    public CertificateAuthenticationProvider(Set<String> allowedSubjectNames) {
        this.allowedSubjectNames = allowedSubjectNames;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Certificate[] certificates = (Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
        if (certificates != null && certificates.length > 0) {
            X509Certificate cert = (X509Certificate) certificates[0];
            String subjectName = cert.getSubjectX500Principal().getName();
            if (allowedSubjectNames.contains(subjectName)) {
                // Create a dummy authentication token
                UserDetails userDetails = User.withUsername(subjectName).password("").roles("USER").build();
                return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            }
        }
        throw new AuthenticationException("Certificate validation failed") {};
    }
}
