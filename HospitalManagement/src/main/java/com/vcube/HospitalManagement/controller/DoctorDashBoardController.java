package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vcube.HospitalManagement.model.Appointment;
import com.vcube.HospitalManagement.model.Doctor;
import com.vcube.HospitalManagement.service.AppointmentService;
import com.vcube.HospitalManagement.service.DoctorService;

@Controller
public class DoctorDashBoardController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    // ================= DASHBOARD =================
    @GetMapping("/doctor/dashboard")
    public String doctorDashboard(Model model, Authentication auth) {

        String username = auth.getName();
        Doctor doctor = doctorService.getDoctorByName(username);

        model.addAttribute("appointments",
                appointmentService.getByDoctor(doctor));

        return "doctorDashBoard";
    }

    // ================= VIEW APPOINTMENTS PAGE =================
    @GetMapping("/doctor/appointments")
    public String viewAppointments(Model model, Authentication auth) {

        String username = auth.getName();
        Doctor doctor = doctorService.getDoctorByName(username);

        model.addAttribute("appointments",
                appointmentService.getByDoctor(doctor));

        return "doctorAppointment"; // NEW HTML PAGE
    }
    
    @GetMapping("/doctor/reschedule/{id}")
    public String doctorReschedule(@PathVariable Long id, Model model) {

        Appointment appointment = appointmentService.getById(id);

        model.addAttribute("appointment", appointment);

        return "reschedule";
    }    
    @GetMapping("/doctor/approve/{id}")
    public String approveAppointment(@PathVariable Long id) {

        Appointment a = appointmentService.getById(id);
        a.setStatus("APPROVED");
        appointmentService.save(a);

        return "redirect:/doctor/appointments";
    }
}