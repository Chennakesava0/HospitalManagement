package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.Appointment;
import com.vcube.HospitalManagement.service.AppointmentService;
import com.vcube.HospitalManagement.service.EmailService;

@Controller   // ✅ REQUIRED
@RequestMapping("/email")   // ✅ IMPORTANT BASE PATH
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppointmentService service;

    // ✅ APPROVE + EMAIL
    @GetMapping("/approve/{id}")
    public String approveAppointment(@PathVariable Long id) {

        Appointment a = service.getById(id);

        // ✅ Update status
        a.setStatus("APPROVED");
        service.save(a);

        // ✅ DEBUG
        System.out.println("Sending email to: " + a.getEmail());

        // ✅ SEND EMAIL
        emailService.sendAppointmentApprovedEmail(
                a.getEmail(),
                a.getPatient().getName(),
                a.getDoctor().getName(),
                a.getDate().toString(),
                a.getTime().toString()
        );

        return "redirect:/doctor/dashboard";
    }

    // ❌ REJECT + EMAIL (OPTIONAL BUT RECOMMENDED)
    @GetMapping("/reject/{id}")
    public String rejectAppointment(@PathVariable Long id) {

        Appointment a = service.getById(id);

        a.setStatus("REJECTED");
        service.save(a);

        emailService.sendEmail(
                a.getEmail(),
                "Appointment Rejected ❌",
                "Dear " + a.getPatient().getName() + ",\n\n"
                + "Your appointment with Dr. " + a.getDoctor().getName() + " has been rejected.\n\n"
                + "Please book another slot.\n\nThank you."
        );

        return "redirect:/doctor/dashboard";
    }
}