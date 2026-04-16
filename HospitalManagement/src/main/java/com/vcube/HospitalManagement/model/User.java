package com.vcube.HospitalManagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 👤 Basic Details
    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true)
    private String phoneNo;

    private String gender;

    // 🔐 Security
    @Column(nullable = false)
    private String password;

    private String role; // ADMIN, DOCTOR, PATIENT
}