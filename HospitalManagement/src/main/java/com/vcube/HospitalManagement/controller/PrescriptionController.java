package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.Prescription;
import com.vcube.HospitalManagement.service.PrescriptionService;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("prescriptions", service.getAllPrescriptions());
        return "prescriptions";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("prescription", new Prescription());
        return "add-prescription";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Prescription prescription) {
        service.savePrescription(prescription);
        return "redirect:/prescriptions";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deletePrescription(id);
        return "redirect:/prescriptions";
    }
}