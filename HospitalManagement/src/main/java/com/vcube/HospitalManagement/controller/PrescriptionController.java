package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.Prescription;
import com.vcube.HospitalManagement.service.PrescriptionService;
import com.vcube.HospitalManagement.service.AppointmentService;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private AppointmentService appointmentService; // ✅ IMPORTANT

    // 📄 View all prescriptions
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("prescriptions", prescriptionService.getAllPrescriptions());
        return "prescription";
    }

    // ➕ Show form
    @GetMapping("/add")
    public String showForm(Model model) {

        model.addAttribute("prescription", new Prescription());

        // ✅ VERY IMPORTANT (without this dropdown will be empty)
        model.addAttribute("appointments", appointmentService.getAllAppointments());

        return "prescriptionForm";
    }

    // 💾 Save prescription
    @PostMapping("/save")
    public String save(@ModelAttribute Prescription prescription) {

        prescriptionService.savePrescription(prescription);

        return "redirect:/prescriptions";
    }

    // ❌ Delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        prescriptionService.deletePrescription(id);

        return "redirect:/prescriptions";
    }
}