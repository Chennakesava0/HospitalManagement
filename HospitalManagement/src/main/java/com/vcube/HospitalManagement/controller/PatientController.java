package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.Patient;
import com.vcube.HospitalManagement.service.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    // 📄 View all patients
    @GetMapping
    public String getAllPatients(Model model) {
        model.addAttribute("patients", service.getAllPatients());
        return "patient";
    }

    // ➕ Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patientForm";
    }

    // 💾 Save patient
    @PostMapping("/save")
    public String savePatient(@ModelAttribute Patient patient) {
        service.savePatient(patient);
        return "redirect:/patients";
    }

    // ❌ Delete patient
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
        return "redirect:/patients";
    }
}