package com.vcube.HospitalManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.HospitalManagement.model.Patient;
import com.vcube.HospitalManagement.repository.PatientRepository;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repo;

    public Patient savePatient(Patient patient) {
        return repo.save(patient);
    }

    public List<Patient> getAllPatients() {
        return repo.findAll();
    }

    public Patient getPatientById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deletePatient(Long id) {
        repo.deleteById(id);
    }

    public List<Patient> searchByName(String name) {
        return repo.findByName(name);
    }
}