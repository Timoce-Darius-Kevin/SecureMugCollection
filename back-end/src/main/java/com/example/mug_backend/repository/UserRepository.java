package com.example.mug_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mug_backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.mugs")
    List<User> findAllWithMugs();
}
