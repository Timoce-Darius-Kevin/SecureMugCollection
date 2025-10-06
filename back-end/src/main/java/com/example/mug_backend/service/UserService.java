package com.example.mug_backend.service;

import com.example.mug_backend.model.User;
import com.example.mug_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository _userRepository;

    public UserService(UserRepository userRepository) {
        this._userRepository = userRepository;
    }

    public List<User> findAll() {
        return _userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return _userRepository.findById(id);
    }

    public Optional<User> findByName(String name) {
        return _userRepository.findByName(name);
    }

    public User save(User user) {

        return _userRepository.save(user);
    }

    public void delete(String id) {
        _userRepository.deleteById(id);
    }
}
