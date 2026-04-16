package com.vcube.HospitalManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.HospitalManagement.model.User;
import com.vcube.HospitalManagement.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // 🔹 Save user (Register)
    public void saveUser(User user) {

        // default role if not selected
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("PATIENT");
        }

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

    // 🔹 Find by email (for login later)
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}