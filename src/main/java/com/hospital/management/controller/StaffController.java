package com.hospital.management.controller;

import com.hospital.management.entity.Staff;
import com.hospital.management.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping("/staff")
    public String staffPage(Model model) {

        model.addAttribute("staff", new Staff());
        model.addAttribute("staffList", staffService.getAllStaff());

        return "staff";
    }

    @PostMapping("/staff")
    public String saveStaff(@ModelAttribute("staff") Staff staff) {

        staffService.saveStaff(staff);

        return "redirect:/staff";
    }

    @GetMapping("/staff/edit/{id}")
    public String editStaff(
            @PathVariable Long id,
            Model model) {

        model.addAttribute("staff",
                staffService.getStaffById(id));

        model.addAttribute("staffList",
                staffService.getAllStaff());

        return "staff";
    }

    @GetMapping("/staff/delete/{id}")
    public String deleteStaff(
            @PathVariable Long id) {

        staffService.deleteStaff(id);

        return "redirect:/staff";
    }
}