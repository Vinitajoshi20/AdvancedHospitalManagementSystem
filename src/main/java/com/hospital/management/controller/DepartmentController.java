package com.hospital.management.controller;

import com.hospital.management.entity.Department;
import com.hospital.management.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public String departmentPage(Model model) {

        model.addAttribute("department", new Department());
        model.addAttribute(
                "departments",
                departmentService.getAllDepartments()
        );

        return "departments";
    }

    @PostMapping("/departments")
    public String saveDepartment(
            @ModelAttribute Department department) {

        departmentService.saveDepartment(department);

        return "redirect:/departments";
    }
}