package com.example.VocabApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // ✅ Enable CORS using our custom configuration
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // ✅ Disable CSRF (important for stateless APIs)
            .csrf(csrf -> csrf.disable())
            // ✅ Permit all requests to API endpoints
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/", "/api/**").permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }

    // ✅ Define global CORS configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow all origins for testing (you can restrict to your frontend domain later)
        config.setAllowedOriginPatterns(List.of("*"));
        // Allowed HTTP methods
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Allow all headers
        config.setAllowedHeaders(List.of("*"));
        // Allow cookies/authorization headers if needed
        config.setAllowCredentials(true);

        // Apply CORS config to all endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
