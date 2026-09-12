package com.hospital.management.service;

import com.hospital.management.entity.Appointment;
import com.hospital.management.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository) {

        this.appointmentRepository =
                appointmentRepository;
    }

    public Appointment saveAppointment(
            Appointment appointment) {

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {

        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByPatientName(
            String patientName) {

        return appointmentRepository
                .findByPatientName(patientName);
    }

    public List<Appointment> getAppointmentsByDoctorName(
            String doctorName) {

        return appointmentRepository
                .findByDoctorName(doctorName);
    }

    public Appointment getAppointmentById(Long id) {

        return appointmentRepository
                .findById(id)
                .orElse(null);
    }

    public void deleteAppointment(Long id) {

        appointmentRepository.deleteById(id);
    }
}