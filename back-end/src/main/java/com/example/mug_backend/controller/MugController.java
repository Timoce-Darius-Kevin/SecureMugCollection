package com.example.mug_backend.controller;

import com.example.mug_backend.service.MugService;
import com.example.mug_backend.service.Auth0UserService;
import com.example.mug_backend.model.Mug;
import com.example.mug_backend.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

@RestController
@RequestMapping("api/mugs")
public class MugController {
    private final MugService mugService;
    private final Auth0UserService auth0UserService;

    public MugController(MugService mugService, Auth0UserService auth0UserService) {
        this.mugService = mugService;
        this.auth0UserService = auth0UserService;
    }

    @GetMapping
    public ResponseEntity<List<Mug>> getAllMugs(@AuthenticationPrincipal Jwt jwt) {
        User currentUser = auth0UserService.getUserFromJwt(jwt).orElse(null);
        
        if (currentUser != null && currentUser.getRole() == com.example.mug_backend.constants.Roles.ADMIN) {
            return ResponseEntity.ok(mugService.findAll());
        } else {
            String userId = jwt.getSubject();
            return ResponseEntity.ok(mugService.findByUserId(userId));
        }
    }

    @PostMapping
    public ResponseEntity<Mug> createMug(@RequestBody Mug mug, @AuthenticationPrincipal Jwt jwt) {
        User currentUser = auth0UserService.getOrCreateUserFromJwt(jwt);
        mug.setUser(currentUser);
        Mug created = mugService.save(mug);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mug> update(@PathVariable Long id, @RequestBody Mug mug, @AuthenticationPrincipal Jwt jwt) {
        Mug existingMug = mugService.findById(id);
        User currentUser = auth0UserService.getOrCreateUserFromJwt(jwt);
        
        // Add ownership check
        if (existingMug == null || !existingMug.getUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.notFound().build();
        }
        
        Mug updated = mugService.update(id, mug);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        Mug existingMug = mugService.findById(id);
        User currentUser = auth0UserService.getOrCreateUserFromJwt(jwt);
        
        // Add ownership check
        if (existingMug == null || !existingMug.getUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.notFound().build();
        }
        
        boolean deleted = mugService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}