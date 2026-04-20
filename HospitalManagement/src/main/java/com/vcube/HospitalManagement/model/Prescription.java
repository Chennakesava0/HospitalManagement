package com.vcube.HospitalManagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medicines;
    private String notes;
    
    private String details;

    // 🔗 One prescription → one appointment
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}