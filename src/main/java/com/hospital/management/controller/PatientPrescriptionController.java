package com.hospital.management.controller;

import com.hospital.management.entity.User;
import com.hospital.management.service.PrescriptionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientPrescriptionController {

    private final PrescriptionService prescriptionService;

    public PatientPrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/my-prescriptions")
    public String myPrescriptions(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "prescriptions",
                prescriptionService.getPrescriptionsByPatientName(user.getName())
        );

        return "patient-prescriptions";
    }
}