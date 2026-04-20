package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.Appointment;
import com.vcube.HospitalManagement.model.Prescription;
import com.vcube.HospitalManagement.service.AppointmentService;
import com.vcube.HospitalManagement.service.PrescriptionService;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private AppointmentService appointmentService;

    // ================= ADMIN =================

    // 📄 View all prescriptions
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("prescriptions", prescriptionService.getAllPrescriptions());
        return "prescription";
    }

    // ➕ Show form (ADMIN)
    @GetMapping("/add")
    public String showForm(Model model) {

        model.addAttribute("prescription", new Prescription());
        model.addAttribute("appointments", appointmentService.getAllAppointments());

        return "prescriptionForm";
    }

    // 💾 Save (ADMIN)
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

    // ================= DOCTOR =================

    // 🔥 Open doctor prescription page
    @GetMapping("/add/{appointmentId}")
    public String showPrescriptionForm(@PathVariable Long appointmentId, Model model) {

        model.addAttribute("appointmentId", appointmentId);

        return "doctorprescription"; // ✅ your HTML file
    }

    // 🔥 Save from doctor dashboard
    @PostMapping("/save-from-doctor")
    public String saveFromDoctor(@RequestParam Long appointmentId,
                                @RequestParam String details,
                                Authentication auth) {

        Appointment appointment = appointmentService.getById(appointmentId);

        // ✅ Safety check
        if (appointment == null) {
            return "redirect:/doctor/dashboard?error=invalid";
        }

        // 🔐 Ensure doctor is correct (optional but recommended)
        String loggedInDoctor = auth.getName();
        if (!appointment.getDoctor().getName().equals(loggedInDoctor)) {
            return "redirect:/doctor/dashboard?error=unauthorized";
        }

        Prescription p = new Prescription();
        p.setAppointment(appointment);
        p.setDetails(details);

        prescriptionService.savePrescription(p);

        return "redirect:/doctor/dashboard"; // ✅ FIXED
    }
}