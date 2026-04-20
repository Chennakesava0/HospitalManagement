package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.User;
import com.vcube.HospitalManagement.service.UserService;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService service;

    // 🔹 Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 🔹 Register Page
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // 🔹 Save User
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user) {
        service.saveUser(user);
        return "redirect:/login";
    }

    // 🔹 Patient Dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    

    // 🔹 Home redirect
    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
}