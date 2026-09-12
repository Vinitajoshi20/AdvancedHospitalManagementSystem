package com.hospital.management.controller;

import com.hospital.management.entity.Doctor;
import com.hospital.management.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public String doctorPage(Model model) {

        model.addAttribute("doctor", new Doctor());

        model.addAttribute(
                "doctors",
                doctorService.getAllDoctors()
        );

        return "doctors";
    }

    @PostMapping("/doctors")
    public String saveDoctor(
            @ModelAttribute("doctor") Doctor doctor) {

        doctorService.saveDoctor(doctor);

        return "redirect:/doctors";
    }

    @GetMapping("/doctors/edit/{id}")
    public String editDoctor(
            @PathVariable Long id,
            Model model) {

        Doctor doctor = doctorService.getDoctorById(id);

        model.addAttribute("doctor", doctor);

        model.addAttribute(
                "doctors",
                doctorService.getAllDoctors()
        );

        return "doctors";
    }

    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(
            @PathVariable Long id) {

        doctorService.deleteDoctor(id);

        return "redirect:/doctors";
    }
}