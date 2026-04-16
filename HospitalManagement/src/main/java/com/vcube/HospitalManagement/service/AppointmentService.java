package com.vcube.HospitalManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.HospitalManagement.model.*;
import com.vcube.HospitalManagement.repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repo;

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