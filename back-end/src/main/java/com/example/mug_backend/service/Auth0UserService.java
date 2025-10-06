package com.example.mug_backend.service;

import com.example.mug_backend.model.User;
import com.example.mug_backend.repository.UserRepository;
import com.example.mug_backend.constants.Roles;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Auth0UserService {
    private final UserRepository _userRepository;

    public Auth0UserService(UserRepository userRepository) {
        this._userRepository = userRepository;
    }

    public User getOrCreateUserFromJwt(Jwt jwt) {
        String userId = jwt.getSubject();
        String email = jwt.getClaim("email");
        String name = jwt.getClaim("name");

        Optional<User> existingUser = _userRepository.findById(userId);

        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            // Set default role
            User newUser = new User(userId, email, name, Roles.ADMIN);
            return _userRepository.save(newUser);
        }
    }

    public Optional<User> getUserFromJwt(Jwt jwt) {
        String userId = jwt.getSubject();
        return _userRepository.findById(userId);
    }
}