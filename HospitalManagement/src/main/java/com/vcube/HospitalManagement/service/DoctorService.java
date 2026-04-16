package com.vcube.HospitalManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.HospitalManagement.model.Doctor;
import com.vcube.HospitalManagement.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repo;

    // Save Doctor
    public Doctor saveDoctor(Doctor doctor) {
        return repo.save(doctor);
    }

    // Get all Doctors
    public List<Doctor> getAllDoctors() {
        return repo.findAll();
    }

    // Get Doctor by ID
    public Doctor getDoctorById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Delete Doctor
    public void deleteDoctor(Long id) {
        repo.deleteById(id);
    }

    // Find by name (optional)
    public Doctor getDoctorByName(String name) {
        return repo.findByName(name).orElse(null);
    }
}