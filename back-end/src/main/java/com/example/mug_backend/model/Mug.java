package com.example.mug_backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;

@Entity
public class Mug{
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String material;
    private String description;
    private double diameter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Mug() {
    }

    public Mug(String name, String material, String description, double diameter, User user) {
        this.name = name;
        this.material = material;
        this.description = description;
        this.diameter = diameter;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User currentUser) {
        this.user = currentUser;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public String getDescription() {
        return description;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }
}