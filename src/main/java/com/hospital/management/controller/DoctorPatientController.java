package com.hospital.management.controller;

import com.hospital.management.entity.Patient;
import com.hospital.management.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DoctorPatientController {

    private final PatientService patientService;

    public DoctorPatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/doctor/patients")
    public String doctorPatients(
            @RequestParam(required = false) String search,
            Model model) {

        List<Patient> patients;

        if (search != null && !search.trim().isEmpty()) {

            patients = patientService.searchPatients(search);

        } else {

            patients = patientService.getAllPatients();

        }

        model.addAttribute("patients", patients);

        model.addAttribute("search", search);

        return "doctor-patients";
    }


    @GetMapping("/doctor/patients/view/{id}")
    public String viewPatient(
            @PathVariable Long id,
            Model model) {

        Patient patient =
                patientService.getPatientById(id);

        if (patient == null) {

            return "redirect:/doctor/patients";

        }

        model.addAttribute("patient", patient);

        return "doctor-patient-view";
    }

}