package com.vcube.HospitalManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${email.from-address}")
    private String from;

    // ✅ COMMON METHOD
    public void sendEmail(String to, String subject, String text) {

        System.out.println("Sending email to: " + to);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from); // 🔥 IMPORTANT
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);

        System.out.println("Email sent successfully!");
    }

    // ✅ APPROVAL EMAIL
    public void sendAppointmentApprovedEmail(String to,
                                             String patientName,
                                             String doctorName,
                                             String date,
                                             String time) {

        String subject = "Appointment Approved ✅";

        String message = "Dear " + patientName + ",\n\n"
                + "Your appointment has been APPROVED.\n\n"
                + "Doctor: " + doctorName + "\n"
                + "Date: " + date + "\n"
                + "Time: " + time + "\n\n"
                + "Please visit the hospital on time.\n\n"
                + "Thank you.";

        sendEmail(to, subject, message);
    }

    // ✅ OPTIONAL (Reject email)
    public void sendAppointmentRejectedEmail(String to,
                                             String patientName,
                                             String doctorName) {

        String subject = "Appointment Rejected ❌";

        String message = "Dear " + patientName + ",\n\n"
                + "Your appointment with Dr. " + doctorName + " has been REJECTED.\n\n"
                + "Please book another slot.\n\n"
                + "Thank you.";

        sendEmail(to, subject, message);
    }
}