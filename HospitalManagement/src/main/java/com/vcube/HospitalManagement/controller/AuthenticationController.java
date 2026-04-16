package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vcube.HospitalManagement.model.User;
import com.vcube.HospitalManagement.service.UserService;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService service;

    // 🔹 Show Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 🔹 Show Register Page
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

    // 🔹 Dashboard
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