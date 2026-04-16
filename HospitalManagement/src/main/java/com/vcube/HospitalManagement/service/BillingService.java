package com.vcube.HospitalManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.HospitalManagement.model.Billing;
import com.vcube.HospitalManagement.repository.BillingRepository;

import java.util.List;

@Service
public class BillingService {

    @Autowired
    private BillingRepository repo;

    public Billing saveBill(Billing billing) {
        return repo.save(billing);
    }

    public List<Billing> getAllBills() {
        return repo.findAll();
    }

    public Billing getBillById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Billing> getByPaymentStatus(String status) {
        return repo.findByPaymentStatus(status);
    }

    public void deleteBill(Long id) {
        repo.deleteById(id);
    }
}