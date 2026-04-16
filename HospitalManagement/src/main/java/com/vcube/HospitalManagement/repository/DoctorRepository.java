package com.vcube.HospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vcube.HospitalManagement.model.Doctor;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Optional: search by name
    Optional<Doctor> findByName(String name);
}