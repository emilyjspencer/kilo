package com.example.kiloexperiment.authkiloexperiment.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.kiloexperiment.authkiloexperiment.models.Permission.*;
import static com.example.kiloexperiment.authkiloexperiment.models.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers(GET, "/api/jobs/**").hasAnyRole(ADMIN.name(), CONSULTANT.name(), ACCOUNT_MANAGER.name())
                                .requestMatchers(GET, "/api/consultants/**").hasAnyRole(ADMIN.name(),  ACCOUNT_MANAGER.name())
                                .requestMatchers(GET, "/api/accountManagers/**").hasRole(ADMIN.name())
                        .requestMatchers(GET, "/api/jobs/**").hasAnyAuthority(ADMIN_READ.getPermission(), ACCOUNT_MANAGER_READ.getPermission(), CONSULTANT_READ.getPermission())
                                .requestMatchers(GET, "/api/consultants/**").hasAnyAuthority(ADMIN_READ.getPermission(), ACCOUNT_MANAGER_READ.getPermission())
                        .requestMatchers(GET, "/api/accountManagers/**").hasAnyAuthority(ADMIN_READ.getPermission())

                        .requestMatchers(DELETE, "/api/jobs/**").hasAnyAuthority(ADMIN_DELETE.getPermission(), ACCOUNT_MANAGER_DELETE.getPermission())
                        .requestMatchers(DELETE, "/api/accountManagers/**").hasAnyAuthority(ADMIN_DELETE.getPermission())
                        .requestMatchers(DELETE, "/api/consultants/**").hasAnyAuthority(ADMIN_DELETE.getPermission())

                        .requestMatchers(PUT, "/api/consultants/**").hasAnyAuthority(ADMIN_UPDATE.getPermission())
                        .requestMatchers(PUT, "/api/jobs/**").hasAnyAuthority(ADMIN_UPDATE.getPermission(), ACCOUNT_MANAGER_UPDATE.getPermission())
                        .requestMatchers(PUT, "/api/accountManagers/**").hasAnyAuthority(ADMIN_UPDATE.getPermission())

                        .requestMatchers(POST, "/api/consultants/**").hasAnyAuthority(ADMIN_UPDATE.getPermission())
                        .requestMatchers(POST, "/api/jobs/**").hasAnyAuthority(ADMIN_UPDATE.getPermission(), ACCOUNT_MANAGER_UPDATE.getPermission())
                        .requestMatchers(POST, "/api/accountManagers/**").hasAnyAuthority(ADMIN_UPDATE.getPermission())

                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    }





}