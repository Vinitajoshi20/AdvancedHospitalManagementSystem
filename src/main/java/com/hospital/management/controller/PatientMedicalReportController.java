package com.hospital.management.controller;

import com.hospital.management.entity.User;
import com.hospital.management.service.MedicalReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientMedicalReportController {

    private final MedicalReportService medicalReportService;

    public PatientMedicalReportController(MedicalReportService medicalReportService) {
        this.medicalReportService = medicalReportService;
    }

    @GetMapping("/my-medical-reports")
    public String myMedicalReports(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "reports",
                medicalReportService.getMedicalReportsByPatientName(user.getName())
        );

        return "patient-medical-reports";
    }
}