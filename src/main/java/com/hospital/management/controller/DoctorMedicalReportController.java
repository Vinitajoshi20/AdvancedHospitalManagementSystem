package com.hospital.management.controller;

import com.hospital.management.entity.MedicalReport;
import com.hospital.management.entity.User;
import com.hospital.management.service.MedicalReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DoctorMedicalReportController {

    private final MedicalReportService medicalReportService;

    public DoctorMedicalReportController(MedicalReportService medicalReportService) {
        this.medicalReportService = medicalReportService;
    }

    @GetMapping("/doctor/medical-reports")
    public String doctorMedicalReports(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("doctorName", user.getName());
        model.addAttribute("medicalReport", new MedicalReport());
        model.addAttribute(
                "reports",
                medicalReportService.getAllMedicalReports()
        );

        return "doctor-medical-reports";
    }

    @PostMapping("/doctor/medical-reports")
    public String saveMedicalReport(
            @ModelAttribute("medicalReport") MedicalReport medicalReport,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        medicalReportService.saveMedicalReport(medicalReport);

        return "redirect:/doctor/medical-reports";
    }
}