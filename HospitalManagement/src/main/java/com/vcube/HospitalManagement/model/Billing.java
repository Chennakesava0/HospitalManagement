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

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public double getConsultationFee() {
//		return consultationFee;
//	}
//
//	public void setConsultationFee(double consultationFee) {
//		this.consultationFee = consultationFee;
//	}
//
//	public double getMedicineCost() {
//		return medicineCost;
//	}
//
//	public void setMedicineCost(double medicineCost) {
//		this.medicineCost = medicineCost;
//	}
//
//	public double getTotalAmount() {
//		return totalAmount;
//	}
//
//	public void setTotalAmount(double totalAmount) {
//		this.totalAmount = totalAmount;
//	}
//
//	public String getPaymentStatus() {
//		return paymentStatus;
//	}
//
//	public void setPaymentStatus(String paymentStatus) {
//		this.paymentStatus = paymentStatus;
//	}
//
//	public LocalDate getBillDate() {
//		return billDate;
//	}
//
//	public void setBillDate(LocalDate billDate) {
//		this.billDate = billDate;
//	}
//
//	public Appointment getAppointment() {
//		return appointment;
//	}
//
//	public void setAppointment(Appointment appointment) {
//		this.appointment = appointment;
//	}
//    
    
}