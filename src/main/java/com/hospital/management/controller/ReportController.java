package com.hospital.management.controller;

import com.hospital.management.entity.Report;
import com.hospital.management.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    public String reports(Model model) {

        model.addAttribute("reports", reportService.getAllReports());

        return "report";
    }

    @PostMapping("/reports/generate")
    public String generateReport(
            @RequestParam("reportType") String reportType,
            @RequestParam("fromDate") String fromDate,
            @RequestParam("toDate") String toDate,
            Model model) {

        Report report = new Report();

        report.setReportType(reportType);
        report.setFromDate(LocalDate.parse(fromDate));
        report.setToDate(LocalDate.parse(toDate));

        reportService.saveReport(report);

        model.addAttribute("reports", reportService.getAllReports());
        model.addAttribute("message", "Report generated and saved successfully!");

        return "report";
    }
}