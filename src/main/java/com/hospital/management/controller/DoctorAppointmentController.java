package com.hospital.management.controller;

import com.hospital.management.entity.Appointment;
import com.hospital.management.entity.User;
import com.hospital.management.service.AppointmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DoctorAppointmentController {

    private final AppointmentService appointmentService;

    public DoctorAppointmentController(
            AppointmentService appointmentService) {

        this.appointmentService = appointmentService;
    }


    // Doctor Appointment Management Page
    @GetMapping("/doctor/appointments")
    public String doctorAppointments(
            HttpSession session,
            Model model) {

        User user =
                (User) session.getAttribute("loggedInUser");

        // Login check
        if (user == null) {
            return "redirect:/login";
        }

        List<Appointment> appointments =
                appointmentService
                        .getAppointmentsByDoctorName(
                                user.getName()
                        );

        model.addAttribute(
                "appointments",
                appointments
        );

        return "doctor-appointments";
    }


    // Accept Appointment
    @GetMapping("/doctor/appointments/accept/{id}")
    public String acceptAppointment(
            @PathVariable Long id,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Appointment appointment =
                appointmentService.getAppointmentById(id);

        if (appointment != null) {

            // Make sure appointment belongs to this doctor
            if (appointment.getDoctorName()
                    .equals(user.getName())) {

                appointment.setStatus("Accepted");

                appointmentService.saveAppointment(
                        appointment
                );
            }
        }

        return "redirect:/doctor/appointments";
    }


    // Complete Appointment
    @GetMapping("/doctor/appointments/complete/{id}")
    public String completeAppointment(
            @PathVariable Long id,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Appointment appointment =
                appointmentService.getAppointmentById(id);

        if (appointment != null) {

            if (appointment.getDoctorName()
                    .equals(user.getName())) {

                appointment.setStatus("Completed");

                appointmentService.saveAppointment(
                        appointment
                );
            }
        }

        return "redirect:/doctor/appointments";
    }


    // Cancel Appointment
    @GetMapping("/doctor/appointments/cancel/{id}")
    public String cancelAppointment(
            @PathVariable Long id,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Appointment appointment =
                appointmentService.getAppointmentById(id);

        if (appointment != null) {

            if (appointment.getDoctorName()
                    .equals(user.getName())) {

                appointment.setStatus("Cancelled");

                appointmentService.saveAppointment(
                        appointment
                );
            }
        }

        return "redirect:/doctor/appointments";
    }
}