package com.example.kiloexperiment.authkiloexperiment.services;

import com.example.kiloexperiment.authkiloexperiment.dtos.AuthenticationRequest;
import com.example.kiloexperiment.authkiloexperiment.dtos.AuthenticationResponse;
import com.example.kiloexperiment.authkiloexperiment.dtos.RegistrationRequest;

public interface AuthenticationService {

    AuthenticationResponse createUser(RegistrationRequest signupRequest);

    AuthenticationResponse authenticate(AuthenticationRequest request);

}
