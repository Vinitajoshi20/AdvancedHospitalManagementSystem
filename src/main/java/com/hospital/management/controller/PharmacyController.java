package com.hospital.management.controller;

import com.hospital.management.entity.Pharmacy;
import com.hospital.management.repository.MedicineRepository;
import com.hospital.management.repository.PatientRepository;
import com.hospital.management.service.PharmacyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pharmacy")
public class PharmacyController {

    private final PharmacyService pharmacyService;
    private final PatientRepository patientRepository;
    private final MedicineRepository medicineRepository;

    public PharmacyController(
            PharmacyService pharmacyService,
            PatientRepository patientRepository,
            MedicineRepository medicineRepository) {

        this.pharmacyService = pharmacyService;
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
    }

    @GetMapping
    public String showPharmacy(Model model) {

        model.addAttribute(
                "pharmacies",
                pharmacyService.getAllPharmacies()
        );

        return "pharmacy";
    }

    @GetMapping("/add")
    public String showAddPharmacyForm(Model model) {

        model.addAttribute("pharmacy", new Pharmacy());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("medicines", medicineRepository.findAll());

        return "add-pharmacy";
    }

    @PostMapping("/save")
    public String savePharmacy(
            @ModelAttribute Pharmacy pharmacy) {

        pharmacyService.savePharmacy(pharmacy);

        return "redirect:/pharmacy";
    }
}