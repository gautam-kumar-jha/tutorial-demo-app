package com.javaguidesl.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.x509.SubjectDnX509PrincipalExtractor;
import org.springframework.security.web.authentication.preauth.x509.X509PrincipalExtractor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig  {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().authenticated()
                )
                .x509(x509 -> x509
                        .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
                        .userDetailsService(userDetailsService())
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public X509PrincipalExtractor x509PrincipalExtractor() {
        SubjectDnX509PrincipalExtractor extractor = new SubjectDnX509PrincipalExtractor();
        extractor.setSubjectDnRegex("CN=(.*?)(?:,|$)");
        return extractor;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            return User.withUsername(username).password("").authorities("USER").build();
        };
    }
}
