package com.example.VocabApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/**").permitAll() // Allow public access to all API endpoints
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable()) // Disable CSRF for API endpoints
                .oauth2ResourceServer(oauth2 -> oauth2.disable()); // Disable OAuth2 resource server

        return http.build();
    }
}