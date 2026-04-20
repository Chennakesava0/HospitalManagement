package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.Appointment;
import com.vcube.HospitalManagement.service.AppointmentService;
import com.vcube.HospitalManagement.service.EmailService;

@Controller
@RequestMapping("/doctor/appointments")
public class DoctorAppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private EmailService emailService;

    // ================= APPROVE =================
    @GetMapping("/approve/{id}")
    public String approve(@PathVariable Long id) {

        Appointment a = appointmentService.getById(id);
        a.setStatus("APPROVED");
        appointmentService.save(a);

        emailService.sendAppointmentApprovedEmail(
                a.getEmail(),
                a.getPatient().getName(),
                a.getDoctor().getName(),
                a.getDate().toString(),
                a.getTime().toString()
        );

        return "redirect:/doctor/appointments";
    }

    // ================= REJECT =================
    @GetMapping("/reject/{id}")
    public String reject(@PathVariable Long id) {

        Appointment a = appointmentService.getById(id);
        a.setStatus("REJECTED");
        appointmentService.save(a);

        emailService.sendEmail(
                a.getEmail(),
                "Appointment Rejected ❌",
                "Your appointment with Dr. " + a.getDoctor().getName()
                        + " has been rejected. You can reschedule it."
        );

        return "redirect:/doctor/appointments";
    }
}