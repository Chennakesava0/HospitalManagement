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

    private String status; // PENDING, APPROVED, REJECTED
    
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

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public LocalDate getDate() {
//		return date;
//	}
//
//	public void setDate(LocalDate date) {
//		this.date = date;
//	}
//
//	public LocalTime getTime() {
//		return time;
//	}
//
//	public void setTime(LocalTime time) {
//		this.time = time;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public Doctor getDoctor() {
//		return doctor;
//	}
//
//	public void setDoctor(Doctor doctor) {
//		this.doctor = doctor;
//	}
//
//	public Patient getPatient() {
//		return patient;
//	}
//
//	public void setPatient(Patient patient) {
//		this.patient = patient;
//	}
//
//	public Prescription getPrescription() {
//		return prescription;
//	}
//
//	public void setPrescription(Prescription prescription) {
//		this.prescription = prescription;
//	}
//
//	public Billing getBilling() {
//		return billing;
//	}
//
//	public void setBilling(Billing billing) {
//		this.billing = billing;
//	}
//    
    
}