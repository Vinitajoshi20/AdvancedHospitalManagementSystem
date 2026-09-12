package com.hospital.management.controller;

import com.hospital.management.entity.Prescription;
import com.hospital.management.entity.User;
import com.hospital.management.service.PrescriptionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class DoctorPrescriptionController {

    private final PrescriptionService prescriptionService;

    public DoctorPrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/doctor/prescriptions")
    public String doctorPrescriptions(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("prescription", new Prescription());

        return "doctor-prescriptions";
    }

    @PostMapping("/doctor/prescriptions")
    public String savePrescription(
            @ModelAttribute("prescription") Prescription prescription,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        prescription.setDoctorName(user.getName());

        prescriptionService.savePrescription(prescription);

        return "redirect:/doctor/prescriptions";
    }
}