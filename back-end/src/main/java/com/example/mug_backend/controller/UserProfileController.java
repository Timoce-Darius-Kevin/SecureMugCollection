package com.example.mug_backend.controller;

import com.example.mug_backend.model.User;
import com.example.mug_backend.service.Auth0UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/profile")
public class UserProfileController {

    private final Auth0UserService auth0UserService;

    public UserProfileController(Auth0UserService auth0UserService) {
        this.auth0UserService = auth0UserService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        User currentUser = auth0UserService.getOrCreateUserFromJwt(jwt);
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/check-admin")
    public ResponseEntity<Boolean> checkAdmin(@AuthenticationPrincipal Jwt jwt) {
        User currentUser = auth0UserService.getOrCreateUserFromJwt(jwt);
        boolean isAdmin = currentUser.getRole() == com.example.mug_backend.constants.Roles.ADMIN;
        return ResponseEntity.ok(isAdmin);
    }
}