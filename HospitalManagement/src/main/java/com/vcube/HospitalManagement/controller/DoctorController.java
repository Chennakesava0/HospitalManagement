package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.Doctor;
import com.vcube.HospitalManagement.service.DoctorService;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService service;

    // ================= LIST =================
    @GetMapping
    public String getAllDoctors(Model model) {
        model.addAttribute("doctors", service.getAllDoctors());
        return "doctors";
    }

    // ================= ADD FORM =================
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "add-doctor";
    }

    // ================= SAVE =================
    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute Doctor doctor) {
        service.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    // ================= DELETE =================
    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {
        service.deleteDoctor(id);
        return "redirect:/doctors";
    }

    // ================= EDIT (OPTIONAL) =================
    @GetMapping("/edit/{id}")
    public String editDoctor(@PathVariable Long id, Model model) {
        model.addAttribute("doctor", service.getDoctorById(id));
        return "add-doctor"; // reuse same form
    }
}