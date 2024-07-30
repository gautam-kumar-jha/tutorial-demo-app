package com.javaguidesl.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Set;
import java.util.HashSet;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        Set<String> allowedSubjectNames = new HashSet<>();
        allowedSubjectNames.add("CN = client");
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new CertificateAuthenticationProvider(allowedSubjectNames), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

