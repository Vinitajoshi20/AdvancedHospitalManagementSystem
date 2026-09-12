package com.hospital.management.controller;

import com.hospital.management.entity.MedicalReport;
import com.hospital.management.entity.User;
import com.hospital.management.service.AiMedicalSummaryService;
import com.hospital.management.service.MedicalReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PatientMedicalSummaryController {

    private final MedicalReportService medicalReportService;
    private final AiMedicalSummaryService aiMedicalSummaryService;

    public PatientMedicalSummaryController(
            MedicalReportService medicalReportService,
            AiMedicalSummaryService aiMedicalSummaryService) {

        this.medicalReportService = medicalReportService;
        this.aiMedicalSummaryService = aiMedicalSummaryService;
    }

    @GetMapping("/my-medical-summary/{id}")
    public String medicalSummary(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        MedicalReport report = medicalReportService.getMedicalReportById(id);

        if (report == null) {
            return "redirect:/my-medical-reports";
        }

        // Patient can only view their own medical report
        if (report.getPatientName() == null ||
                !report.getPatientName().equals(user.getName())) {

            return "redirect:/my-medical-reports";
        }

        String summary = aiMedicalSummaryService.generateSummary(report);

        model.addAttribute("report", report);
        model.addAttribute("summary", summary);
        model.addAttribute("patientName", user.getName());

        return "patient-medical-summary";
    }
}