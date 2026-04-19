package com.vcube.HospitalManagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime time;

    private String status; // BOOKED, COMPLETED, CANCELLED
    
    @Column
    private String email;

    // 🔗 Many appointments → one doctor
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // 🔗 Many appointments → one patient
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // 🔗 One appointment → one prescription
    @OneToOne(mappedBy = "appointment")
    private Prescription prescription;

    // 🔗 One appointment → one bill
    @OneToOne(mappedBy = "appointment")
    private Billing billing;
}