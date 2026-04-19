package com.vcube.HospitalManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendAppointmentEmail(String toEmail, String doctor, String date, String time) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Appointment Confirmation");

        message.setText(
                "Your appointment is confirmed.\n\n" +
                "Doctor: " + doctor +
                "\nDate: " + date +
                "\nTime: " + time +
                "\n\nThank you!"
        );

        mailSender.send(message);
    }
}