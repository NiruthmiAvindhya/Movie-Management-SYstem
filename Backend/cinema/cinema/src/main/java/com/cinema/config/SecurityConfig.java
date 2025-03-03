package com.cinema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF for development purposes
                .authorizeRequests()
                .antMatchers("/vendors/register", "/vendors/login","/consumers/register", "/consumers/login","/vendors/movies","/bookings/create","/vendors/{vendorId}","/vendors/{vendorId}/movies","/bookings/book/{movieId}","/customers/book","/customers/book/{movieId}","/customers/movies","/vendors/{vendorId}/movies/{movieId}","/tickets/release","/tickets/purchase","/tickets/cancel").permitAll() // Allow these endpoints without authentication
                .anyRequest().authenticated(); // All other endpoints require authentication

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

