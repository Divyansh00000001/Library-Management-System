package com.divyansh.library_management_system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.divyansh.library_management_system.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	SecurityFilterChain securityFilterChain(
	        HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())

	        .sessionManagement(session ->
	                session.sessionCreationPolicy(
	                        SessionCreationPolicy.STATELESS))

	        .authorizeHttpRequests(auth -> auth

	                .requestMatchers(
	                        "/api/auth/**")
	                .permitAll()

	                .requestMatchers(
	                        HttpMethod.GET,
	                        "/api/books/**")
	                .hasAnyRole("USER", "ADMIN")

	                .requestMatchers(
	                        HttpMethod.POST,
	                        "/api/books")
	                .hasRole("ADMIN")

	                .requestMatchers(
	                        HttpMethod.PUT,
	                        "/api/books/**")
	                .hasRole("ADMIN")

	                .requestMatchers(
	                        HttpMethod.DELETE,
	                        "/api/books/**")
	                .hasRole("ADMIN")

	                .requestMatchers(
	                        "/api/books/borrow/**",
	                        "/api/books/return/**")
	                .hasAnyRole("USER", "ADMIN")
	                
	                .requestMatchers("/api/admin/**")
	                .hasRole("ADMIN")

	                .anyRequest()
	                .authenticated())

	        .addFilterBefore(
	                jwtAuthenticationFilter,
	                UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
}