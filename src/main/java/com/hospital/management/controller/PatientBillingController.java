package com.hospital.management.controller;

import com.hospital.management.entity.Bill;
import com.hospital.management.entity.User;
import com.hospital.management.service.BillService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientBillingController {

    private final BillService billService;

    public PatientBillingController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/my-bills")
    public String myBills(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute(
                "bills",
                billService.getBillsByPatientName(user.getName())
        );

        return "patient-bills";
    }

    @GetMapping("/pay-bill/{id}")
    public String paymentPage(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Bill bill = billService.getBillById(id);

        if (bill == null) {
            return "redirect:/my-bills";
        }

        if (!user.getName().equals(bill.getPatientName())) {
            return "redirect:/my-bills";
        }

        if (bill.getDueAmount() == null || bill.getDueAmount() <= 0) {
            return "redirect:/my-bills";
        }

        model.addAttribute("bill", bill);

        return "payment";
    }

    @PostMapping("/pay-bill/{id}")
    public String processPayment(
            @PathVariable Long id,
            @RequestParam String paymentMethod,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Bill bill = billService.getBillById(id);

        if (bill == null) {
            return "redirect:/my-bills";
        }

        if (!user.getName().equals(bill.getPatientName())) {
            return "redirect:/my-bills";
        }

        if (bill.getDueAmount() == null || bill.getDueAmount() <= 0) {
            return "redirect:/my-bills";
        }

        Double dueAmount = bill.getDueAmount();

        Double alreadyPaid =
                bill.getPaidAmount() == null
                        ? 0.0
                        : bill.getPaidAmount();

        bill.setPaidAmount(alreadyPaid + dueAmount);
        bill.setDueAmount(0.0);
        bill.setPaymentMethod(paymentMethod);
        bill.setPaymentStatus("Paid");

        billService.saveBill(bill);

        return "redirect:/payment-success";
    }

    @GetMapping("/payment-success")
    public String paymentSuccess() {
        return "payment-success";
    }
}