package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.Billing;
import com.vcube.HospitalManagement.service.BillingService;
import com.vcube.HospitalManagement.service.AppointmentService;

@Controller
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService service;

    @Autowired
    private AppointmentService appointmentService; // ✅ ADD THIS

    // 📄 View all bills
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("billings", service.getAllBills()); // match HTML
        return "billing";
    }

    // ➕ Show form
    @GetMapping("/add")
    public String showForm(Model model) {

        model.addAttribute("billing", new Billing());

        // ✅ VERY IMPORTANT (without this dropdown empty)
        model.addAttribute("appointments", appointmentService.getAllAppointments());

        return "billingForm";
    }

    // 💾 Save
    @PostMapping("/save")
    public String save(@ModelAttribute Billing billing) {

        // ✅ Optional: auto calculate total
        double total = billing.getConsultationFee() + billing.getMedicineCost();
        billing.setTotalAmount(total);

        service.saveBill(billing);

        return "redirect:/billing";
    }

    // ❌ Delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteBill(id);
        return "redirect:/billing";
    }
}