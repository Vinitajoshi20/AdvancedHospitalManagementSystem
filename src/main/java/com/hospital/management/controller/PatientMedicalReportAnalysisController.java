package com.hospital.management.controller;

import com.hospital.management.entity.MedicalReport;
import com.hospital.management.entity.User;
import com.hospital.management.service.AiMedicalReportAnalysisService;
import com.hospital.management.service.MedicalReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PatientMedicalReportAnalysisController {

    private final MedicalReportService medicalReportService;
    private final AiMedicalReportAnalysisService aiMedicalReportAnalysisService;

    public PatientMedicalReportAnalysisController(
            MedicalReportService medicalReportService,
            AiMedicalReportAnalysisService aiMedicalReportAnalysisService) {

        this.medicalReportService = medicalReportService;
        this.aiMedicalReportAnalysisService = aiMedicalReportAnalysisService;
    }

    @GetMapping("/my-medical-analysis/{id}")
    public String medicalAnalysis(
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

        // Patient can only analyze their own medical report
        if (report.getPatientName() == null ||
                !report.getPatientName().equals(user.getName())) {

            return "redirect:/my-medical-reports";
        }

        String analysis =
                aiMedicalReportAnalysisService.analyzeReport(report);

        model.addAttribute("report", report);
        model.addAttribute("analysis", analysis);
        model.addAttribute("patientName", user.getName());

        return "patient-medical-analysis";
    }
}