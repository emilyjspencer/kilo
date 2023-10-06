package com.example.kiloexperiment.authkiloexperiment.services;

import com.example.kiloexperiment.authkiloexperiment.dtos.UserDTO;
import com.example.kiloexperiment.authkiloexperiment.models.User;
import com.example.kiloexperiment.authkiloexperiment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserDTO> findUserByUsername(String username) {
        Optional<User> userOpt = userRepository.findFirstByUsername(username);

        return userOpt.map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTO.setName(user.getName());
            userDTO.setRole(user.getRole());
            return userDTO;
        });
    }

}