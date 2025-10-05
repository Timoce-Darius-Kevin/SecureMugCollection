package com.example.mug_backend.repository;

import com.example.mug_backend.model.Mug;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final MugRepository mugRepository;

    public DataInitializer(MugRepository mugRepository) {
        this.mugRepository = mugRepository;
    }

    @Override
    public void run(String... args) {
        mugRepository.save(new Mug("Classic Mug", "Ceramic", "A classic white mug", 8.5));
        mugRepository.save(new Mug("Travel Mug", "Stainless Steel", "Keeps drinks hot", 7.0));
        mugRepository.save(new Mug("Espresso Cup", "Porcelain", "Small cup for espresso", 5.0));
    }
}