package com.hospital.management.controller;

import com.hospital.management.entity.User;
import com.hospital.management.service.LabReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientLabReportController {

    private final LabReportService labReportService;

    public PatientLabReportController(LabReportService labReportService) {
        this.labReportService = labReportService;
    }

    @GetMapping("/my-lab-reports")
    public String myLabReports(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "labReports",
                labReportService.getLabReportsByPatientName(user.getName())
        );

        return "patient-lab-reports";
    }
}