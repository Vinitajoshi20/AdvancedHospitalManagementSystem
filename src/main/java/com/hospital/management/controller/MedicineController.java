package com.hospital.management.controller;

import com.hospital.management.entity.Medicine;
import com.hospital.management.service.MedicineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/medicines")
    public String medicines(Model model) {

        model.addAttribute("medicine", new Medicine());
        model.addAttribute("medicines", medicineService.getAllMedicines());

        return "medicines";
    }

    @PostMapping("/medicines")
    public String saveMedicine(@ModelAttribute("medicine") Medicine medicine) {

        medicineService.saveMedicine(medicine);

        return "redirect:/medicines";
    }

    @GetMapping("/medicines/edit/{id}")
    public String editMedicine(@PathVariable Long id, Model model) {

        model.addAttribute("medicine",
                medicineService.getMedicineById(id));

        model.addAttribute("medicines",
                medicineService.getAllMedicines());

        return "medicines";
    }

    @GetMapping("/medicines/delete/{id}")
    public String deleteMedicine(@PathVariable Long id) {

        medicineService.deleteMedicine(id);

        return "redirect:/medicines";
    }
}