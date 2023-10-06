package com.example.kiloexperiment.authkiloexperiment.controllers;

import com.example.kiloexperiment.authkiloexperiment.dtos.UserDTO;
import com.example.kiloexperiment.authkiloexperiment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    @PreAuthorize("isAuthenticated() and (#username == authentication.principal.username or hasRole('ADMIN'))")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) {
        Optional<UserDTO> user = userService.findUserByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{username}/role")
    public ResponseEntity<String> getUserRoleByUsername(@PathVariable String username){
        Optional<UserDTO> user = userService.findUserByUsername(username);
        if(user.isPresent()) {
            String role = user.get().getRole().name();
            return ResponseEntity.ok(role);
        }
        return ResponseEntity.notFound().build();

    }


}