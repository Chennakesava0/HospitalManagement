package com.vcube.HospitalManagement.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.Appointment;
import com.vcube.HospitalManagement.service.AppointmentService;
import com.vcube.HospitalManagement.service.DoctorService;
import com.vcube.HospitalManagement.service.PatientService;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    // ================= VIEW ALL =================
    @GetMapping
    public String getAllAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointment";
    }

    // ================= ADD FORM =================
    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("patients", patientService.getAllPatients());
        return "appointmentForm";
    }

    // ================= SAVE =================
    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute Appointment appointment, Model model) {

        boolean available = appointmentService.isSlotAvailable(
                appointment.getDoctor(),
                appointment.getDate(),
                appointment.getTime()
        );

        if (!available) {
            model.addAttribute("error", "❌ Slot already booked!");
            model.addAttribute("doctors", doctorService.getAllDoctors());
            model.addAttribute("patients", patientService.getAllPatients());
            return "appointmentForm";
        }

        appointmentService.saveAppointment(appointment);

        return "redirect:/appointments";
    }

    // ================= RESCHEDULE PAGE =================
    @GetMapping("/reschedule/{id}")
    public String showRescheduleForm(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getById(id));
        return "reschedule";
    }

    // ================= RESCHEDULE =================
    @PostMapping("/reschedule")
    public String reschedule(@RequestParam Long id,
                             @RequestParam String date,
                             @RequestParam String time,
                             Model model) {

        Appointment appointment = appointmentService.getById(id);

        LocalDate newDate = LocalDate.parse(date);
        LocalTime newTime = LocalTime.parse(time);

        boolean available = appointmentService.isSlotAvailableForReschedule(
                id,
                appointment.getDoctor(),
                newDate,
                newTime
        );

        if (!available) {
            model.addAttribute("error", "❌ Slot already booked!");
            model.addAttribute("appointment", appointment);
            return "reschedule";
        }

        appointment.setDate(newDate);
        appointment.setTime(newTime);
        appointment.setStatus("PENDING");

        appointmentService.save(appointment);

        return "redirect:/appointments";
    }

    // ================= DELETE =================
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }
}