package com.vcube.HospitalManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcube.HospitalManagement.model.Appointment;
import com.vcube.HospitalManagement.model.Doctor;
import com.vcube.HospitalManagement.model.Patient;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // 🔍 Find by doctor
    List<Appointment> findByDoctor(Doctor doctor);

    // 🔍 Find by patient
    List<Appointment> findByPatient(Patient patient);

    // 🔍 Find by date
    List<Appointment> findByDate(LocalDate date);

    // 🔍 Doctor appointments on a specific date
    List<Appointment> findByDoctorAndDate(Doctor doctor, LocalDate date);
}