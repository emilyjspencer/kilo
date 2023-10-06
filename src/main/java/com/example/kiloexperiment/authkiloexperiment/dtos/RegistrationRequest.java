package com.example.kiloexperiment.authkiloexperiment.dtos;

import com.example.kiloexperiment.authkiloexperiment.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private String name;

    private String username;

    private String password;

    private String email;

    private Role role;

}
