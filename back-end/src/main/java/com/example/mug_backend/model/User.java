package com.example.mug_backend.model;

import java.util.ArrayList;
import java.util.List;

import com.example.mug_backend.constants.Roles;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "\"user\"") // Escape "user" to avoid conflicts with SQL reserved keyword
public class User {
    @Id
    private String id; // Auth0 user ID (sub claim)

    private String email;
    private String name;
    
    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Mug> mugs;

    // Constructors
    public User() {
    }

    public User(String id, String email, String name, Roles role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.mugs = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<Mug> getMugs() {
        return mugs;
    }

    public void setMugs(List<Mug> mugs) {
        this.mugs = mugs;
    }
}