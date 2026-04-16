package com.vcube.HospitalManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.HospitalManagement.model.Prescription;
import com.vcube.HospitalManagement.repository.PrescriptionRepository;

import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository repo;

    public Prescription savePrescription(Prescription prescription) {
        return repo.save(prescription);
    }

    public List<Prescription> getAllPrescriptions() {
        return repo.findAll();
    }

    public Prescription getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deletePrescription(Long id) {
        repo.deleteById(id);
    }
}