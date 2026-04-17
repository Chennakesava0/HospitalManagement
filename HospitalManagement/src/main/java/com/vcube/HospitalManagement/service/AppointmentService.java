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

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repo;
    
    public boolean isSlotAvailable(Doctor doctor, LocalDate date, LocalTime time) {
        return !repo.existsByDoctorAndDateAndTime(doctor, date, time);
    }

    public Appointment saveAppointment(Appointment appointment) {
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

    public void deleteAppointment(Long id) {
        repo.deleteById(id);
    }
}