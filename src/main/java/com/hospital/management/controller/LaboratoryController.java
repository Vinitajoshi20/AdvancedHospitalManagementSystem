package com.hospital.management.controller;

import com.hospital.management.entity.Laboratory;
import com.hospital.management.service.LaboratoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LaboratoryController {

    private final LaboratoryService laboratoryService;

    public LaboratoryController(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    @GetMapping("/laboratory")
    public String laboratoryPage(Model model) {
        model.addAttribute("laboratory", new Laboratory());
        model.addAttribute("laboratories", laboratoryService.getAllLaboratories());
        return "laboratory";
    }

    @PostMapping("/laboratory")
    public String saveLaboratory(@ModelAttribute Laboratory laboratory) {
        laboratoryService.saveLaboratory(laboratory);
        return "redirect:/laboratory";
    }
}
