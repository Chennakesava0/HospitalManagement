package com.vcube.HospitalManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.HospitalManagement.model.Appointment;
import com.vcube.HospitalManagement.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Optional: search by name
    Optional<Doctor> findByName(String name);
    
   
    
 }