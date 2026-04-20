package com.vcube.HospitalManagement.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.HospitalManagement.model.Appointment;
import com.vcube.HospitalManagement.model.Doctor;
import com.vcube.HospitalManagement.model.Patient;
import com.vcube.HospitalManagement.repository.AppointmentRepository;
import com.vcube.HospitalManagement.repository.DoctorRepository;
import com.vcube.HospitalManagement.repository.PatientRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repo;

    @Autowired
    private DoctorRepository doctorRepo;

    @Autowired
    private PatientRepository patientRepo;

    public boolean isSlotAvailableForReschedule(Long id, Doctor doctor, LocalDate date, LocalTime time) {
        return !repo.existsByDoctorAndDateAndTimeAndIdNot(doctor, date, time, id);
    }
    
    public boolean isSlotAvailable(Doctor doctor, LocalDate date, LocalTime time) {
        return !repo.existsByDoctorAndDateAndTime(doctor, date, time);
    }

    // 🔥 FIXED SAVE METHOD
    public Appointment saveAppointment(Appointment appointment) {

        // 🔥 Fetch FULL Doctor
        Doctor doctor = doctorRepo.findById(appointment.getDoctor().getId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // 🔥 Fetch FULL Patient
        Patient patient = patientRepo.findById(appointment.getPatient().getId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // 🔥 Set back
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        
        appointment.setStatus("PENDING");

        return repo.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return repo.findAll();
    }

    public List<Appointment> getByDoctor(Doctor doctor) {
        return repo.findByDoctor(doctor);
    }

    public List<Appointment> getByPatient(Patient patient) {
        return repo.findByPatient(patient);
    }

    public List<Appointment> getByDate(LocalDate date) {
        return repo.findByDate(date);
    }
    
    public Appointment getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public void save(Appointment appointment) {
        repo.save(appointment);
    }

    public void deleteAppointment(Long id) {
        repo.deleteById(id);
    }
    
    public List<Appointment> getAppointmentsByDoctor(String name) {
        return repo.findByDoctorName(name);
    }
}