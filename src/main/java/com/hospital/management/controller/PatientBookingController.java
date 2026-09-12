package com.hospital.management.controller;

import com.hospital.management.entity.Appointment;
import com.hospital.management.entity.Doctor;
import com.hospital.management.entity.User;
import com.hospital.management.service.AppointmentService;
import com.hospital.management.service.DoctorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PatientBookingController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    public PatientBookingController(
            AppointmentService appointmentService,
            DoctorService doctorService) {

        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }

    @GetMapping("/book-appointment")
    public String bookAppointmentPage(
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Appointment appointment = new Appointment();

        appointment.setPatientName(user.getName());

        appointment.setStatus("Pending");

        List<Doctor> availableDoctors =
                doctorService.getAvailableDoctors();

        model.addAttribute("appointment", appointment);

        model.addAttribute(
                "availableDoctors",
                availableDoctors
        );

        return "book-appointment";
    }


    @PostMapping("/book-appointment")
    public String bookAppointment(
            @ModelAttribute("appointment")
            Appointment appointment,
            HttpSession session) {

        User user =
                (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        appointment.setPatientName(user.getName());

        appointment.setStatus("Pending");

        appointmentService.saveAppointment(appointment);

        return "redirect:/my-appointments";
    }
}