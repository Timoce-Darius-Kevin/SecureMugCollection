package com.example.mug_backend.service;

import com.example.mug_backend.model.User;
import com.example.mug_backend.repository.UserRepository;
import com.example.mug_backend.constants.Roles;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

        List<String> jwtRoles = jwt.getClaimAsStringList("https://mugapp.com/roles");
        Roles role;
        
        if (jwtRoles != null && !jwtRoles.isEmpty()) {
            // Use role from JWT
            role = jwtRoles.contains("ADMIN") ? Roles.ADMIN : Roles.USER;
        } else {
            // Fallback: email-based admin assignment
            role = isAdminEmail(email) ? Roles.ADMIN : Roles.USER;
        }

        Optional<User> existingUser = _userRepository.findById(userId);

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // Update role if it changed
            if (user.getRole() != role) {
                user.setRole(role);
                return _userRepository.save(user);
            }
            return user;
        } else {
            User newUser = new User(userId, email, name, role);
            return _userRepository.save(newUser);
        }
    }

    private boolean isAdminEmail(String email) {
        // Add your admin emails here
        Set<String> adminEmails = Set.of(
            "admin@example.com",
            "your-actual-email@gmail.com"  // Replace with your email
        );
        return adminEmails.contains(email);
    }


    public Optional<User> getUserFromJwt(Jwt jwt) {
        String userId = jwt.getSubject();
        return _userRepository.findById(userId);
    }
}