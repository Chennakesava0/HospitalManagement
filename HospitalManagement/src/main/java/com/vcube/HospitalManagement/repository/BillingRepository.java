package com.vcube.HospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcube.HospitalManagement.model.Billing;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    // 🔍 Find by payment status
    List<Billing> findByPaymentStatus(String paymentStatus);
}