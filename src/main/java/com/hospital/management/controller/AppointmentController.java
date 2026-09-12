package com.hospital.management.controller;

import com.hospital.management.entity.Appointment;
import com.hospital.management.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Appointment Management Page
    @GetMapping("/appointments")
    public String appointmentPage(Model model) {

        model.addAttribute("appointment", new Appointment());
        model.addAttribute("appointments",
                appointmentService.getAllAppointments());

        return "appointments";
    }

    // Save / Update Appointment
    @PostMapping("/appointments")
    public String saveAppointment(
            @ModelAttribute Appointment appointment) {

        appointmentService.saveAppointment(appointment);

        return "redirect:/appointments";
    }

    // Edit Appointment
    @GetMapping("/appointments/edit/{id}")
    public String editAppointment(
            @PathVariable Long id,
            Model model) {

        Appointment appointment =
                appointmentService.getAppointmentById(id);

        model.addAttribute("appointment", appointment);
        model.addAttribute("appointments",
                appointmentService.getAllAppointments());

        return "appointments";
    }

    // Delete Appointment
    @GetMapping("/appointments/delete/{id}")
    public String deleteAppointment(
            @PathVariable Long id) {

        appointmentService.deleteAppointment(id);

        return "redirect:/appointments";
    }
}