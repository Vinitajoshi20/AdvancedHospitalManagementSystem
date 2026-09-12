package com.hospital.management.controller;

import com.hospital.management.entity.Doctor;
import com.hospital.management.entity.MedicalReport;
import com.hospital.management.entity.Patient;
import com.hospital.management.service.DoctorService;
import com.hospital.management.service.MedicalReportService;
import com.hospital.management.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MedicalReportController {

    private final MedicalReportService medicalReportService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public MedicalReportController(
            MedicalReportService medicalReportService,
            PatientService patientService,
            DoctorService doctorService) {

        this.medicalReportService = medicalReportService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    // Open Medical Reports page
    @GetMapping("/medical-reports")
    public String medicalReportPage(Model model) {

        model.addAttribute("medicalReport", new MedicalReport());

        model.addAttribute(
                "patients",
                patientService.getAllPatients()
        );

        model.addAttribute(
                "doctors",
                doctorService.getAllDoctors()
        );

        model.addAttribute(
                "medicalReports",
                medicalReportService.getAllMedicalReports()
        );

        return "medical-report";
    }

    // Save Medical Report
    @PostMapping("/medical-reports")
    public String saveMedicalReport(
            @ModelAttribute("medicalReport") MedicalReport medicalReport) {

        medicalReportService.saveMedicalReport(medicalReport);

        return "redirect:/medical-reports";
    }

    // Edit Medical Report
    @GetMapping("/medical-reports/edit/{id}")
    public String editMedicalReport(
            @PathVariable Long id,
            Model model) {

        MedicalReport medicalReport =
                medicalReportService.getMedicalReportById(id);

        model.addAttribute("medicalReport", medicalReport);

        model.addAttribute(
                "patients",
                patientService.getAllPatients()
        );

        model.addAttribute(
                "doctors",
                doctorService.getAllDoctors()
        );

        model.addAttribute(
                "medicalReports",
                medicalReportService.getAllMedicalReports()
        );

        return "medical-report";
    }

    // Delete Medical Report
    @GetMapping("/medical-reports/delete/{id}")
    public String deleteMedicalReport(
            @PathVariable Long id) {

        medicalReportService.deleteMedicalReport(id);

        return "redirect:/medical-reports";
    }
}