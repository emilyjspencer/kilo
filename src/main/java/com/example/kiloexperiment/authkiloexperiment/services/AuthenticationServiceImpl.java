package com.example.kiloexperiment.authkiloexperiment.services;

import com.example.kiloexperiment.authkiloexperiment.dtos.AuthenticationRequest;
import com.example.kiloexperiment.authkiloexperiment.dtos.AuthenticationResponse;
import com.example.kiloexperiment.authkiloexperiment.dtos.RegistrationRequest;
import com.example.kiloexperiment.authkiloexperiment.models.User;
import com.example.kiloexperiment.authkiloexperiment.repositories.UserRepository;
import com.example.kiloexperiment.authkiloexperiment.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository.UserDAO userRepository;

    @Autowired
    private JwtUtil jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse createUser(RegistrationRequest signupRequest) {
        if (userRepository.findFirstByUsername(signupRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUsername(signupRequest.getUsername());
        //TODO: sign in with email or username
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(signupRequest.getRole());
        //TODO: make role just "USER"
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().jwt(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        var user = userRepository.findFirstByUsername(authenticationRequest.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().jwt(jwtToken).build();

    }

}

