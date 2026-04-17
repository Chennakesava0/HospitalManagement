package com.vcube.HospitalManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vcube.HospitalManagement.model.User;
import com.vcube.HospitalManagement.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    
    

    // 🔹 Save user (Register)
    @Autowired
    private PasswordEncoder encoder;

    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword())); // ✅ HERE
        repo.save(user);
    }

    // 🔹 Get all users
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    // 🔹 Delete user
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

 // 🔹 Find by email
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    // 🔹 Find by username (for login)
    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    
    }
}