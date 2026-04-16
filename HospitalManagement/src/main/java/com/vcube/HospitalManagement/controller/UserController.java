package com.vcube.HospitalManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.User;
import com.vcube.HospitalManagement.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    // 📄 View all users
    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // ➕ Show add user form
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    // 💾 Save user
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        service.saveUser(user);
        return "redirect:/users";
    }

    // ❌ Delete user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
        return "redirect:/users";
    }
}