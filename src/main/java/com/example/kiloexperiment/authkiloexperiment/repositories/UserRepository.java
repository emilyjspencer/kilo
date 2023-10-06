package com.example.kiloexperiment.authkiloexperiment.repositories;

import com.example.kiloexperiment.authkiloexperiment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {

        Optional<User> findFirstByEmail(String email);

        Optional<User> findFirstByUsername(String username);

}
