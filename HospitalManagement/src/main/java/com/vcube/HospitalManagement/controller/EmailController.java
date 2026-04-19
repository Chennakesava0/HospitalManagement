package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vcube.HospitalManagement.model.Appointment;
import com.vcube.HospitalManagement.service.AppointmentService;
import com.vcube.HospitalManagement.service.EmailService;

public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AppointmentService service;

	@PostMapping("/save")
	public String saveAppointment(@ModelAttribute Appointment appointment) {

	    service.saveAppointment(appointment);   // ✅ correct method

	    emailService.sendAppointmentEmail(
	            appointment.getEmail(),
	            appointment.getDoctor().getName(),
	            appointment.getDate().toString(),
	            appointment.getTime().toString()
	    );

	    return "redirect:/appointments";
	}}
