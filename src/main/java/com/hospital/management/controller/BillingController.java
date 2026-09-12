package com.hospital.management.controller;

import com.hospital.management.entity.Bill;
import com.hospital.management.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BillingController {

    private final BillService billService;

    public BillingController(BillService billService) {
        this.billService = billService;
    }


    // Open Billing Page
    @GetMapping("/billing")
    public String billingPage(Model model) {

        model.addAttribute("bill", new Bill());

        model.addAttribute(
                "bills",
                billService.getAllBills()
        );

        return "billing";
    }


    // Save Bill
    @PostMapping("/billing")
    public String saveBill(
            @ModelAttribute("bill") Bill bill) {

        // Calculate due amount automatically
        if (bill.getTotalAmount() != null &&
                bill.getPaidAmount() != null) {

            bill.setDueAmount(
                    bill.getTotalAmount()
                            - bill.getPaidAmount()
            );
        }

        // Default payment status
        if (bill.getPaymentStatus() == null ||
                bill.getPaymentStatus().isEmpty()) {

            if (bill.getDueAmount() != null &&
                    bill.getDueAmount() <= 0) {

                bill.setPaymentStatus("Paid");

            } else if (bill.getPaidAmount() != null &&
                    bill.getPaidAmount() > 0) {

                bill.setPaymentStatus("Partially Paid");

            } else {

                bill.setPaymentStatus("Pending");
            }
        }

        billService.saveBill(bill);

        return "redirect:/billing";
    }


    // Edit Bill
    @GetMapping("/billing/edit/{id}")
    public String editBill(
            @PathVariable Long id,
            Model model) {

        Bill bill = billService.getBillById(id);

        if (bill == null) {
            return "redirect:/billing";
        }

        model.addAttribute("bill", bill);

        model.addAttribute(
                "bills",
                billService.getAllBills()
        );

        return "billing";
    }


    // Delete Bill
    @GetMapping("/billing/delete/{id}")
    public String deleteBill(
            @PathVariable Long id) {

        billService.deleteBill(id);

        return "redirect:/billing";
    }
}