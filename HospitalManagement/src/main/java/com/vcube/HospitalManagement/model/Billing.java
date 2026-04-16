package com.vcube.HospitalManagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double consultationFee;
    private double medicineCost;
    private double totalAmount;

    private String paymentStatus; // PAID, PENDING
    private LocalDate billDate;

    // 🔗 One bill → one appointment
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}