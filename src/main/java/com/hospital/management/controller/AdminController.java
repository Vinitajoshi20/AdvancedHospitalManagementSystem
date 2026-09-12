package com.hospital.management.controller;

import com.hospital.management.entity.Admin;
import com.hospital.management.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Admin Management Page
    @GetMapping("/admin")
    public String adminPage(Model model) {

        model.addAttribute("admin", new Admin());
        model.addAttribute("admins", adminService.getAllAdmins());

        return "admin";
    }

    // Save / Update Admin
    @PostMapping("/admin")
    public String saveAdmin(@ModelAttribute Admin admin) {

        adminService.saveAdmin(admin);

        return "redirect:/admin";
    }

    // Edit Admin
    @GetMapping("/admin/edit/{id}")
    public String editAdmin(
            @PathVariable Long id,
            Model model) {

        Admin admin = adminService.getAdminById(id);

        model.addAttribute("admin", admin);
        model.addAttribute("admins", adminService.getAllAdmins());

        return "admin";
    }

    // Delete Admin
    @GetMapping("/admin/delete/{id}")
    public String deleteAdmin(@PathVariable Long id) {

        adminService.deleteAdmin(id);

        return "redirect:/admin";
    }
}