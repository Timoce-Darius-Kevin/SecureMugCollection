package com.example.mug_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    public Mug() {
    }

    public Mug(String name, String material, String description, double diameter) {
        this.name = name;
        this.material = material;
        this.description = description;
        this.diameter = diameter;
    }

    public long getId() {
        return id;
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