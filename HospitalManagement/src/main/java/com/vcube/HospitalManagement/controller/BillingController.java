package com.vcube.HospitalManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vcube.HospitalManagement.model.Billing;
import com.vcube.HospitalManagement.service.BillingService;

@Controller
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("bills", service.getAllBills());
        return "billing";
    }

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("billing", new Billing());
        return "add-billing";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Billing billing) {
        service.saveBill(billing);
        return "redirect:/billing";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteBill(id);
        return "redirect:/billing";
    }
}