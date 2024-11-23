package com.example.pr4.controller;

import com.example.pr4.models.Supplier;
import com.example.pr4.repo.SupplierRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping
    public String index(Model model) {
        List<Supplier> suppliers = supplierRepository.findAll();
        model.addAttribute("suppliers", suppliers);
        return "suppliers/index";
    }

    @GetMapping("/new")
    public String newSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliers/new";
    }

    @PostMapping
    public String createSupplier(@Valid @ModelAttribute("supplier") Supplier supplier, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("supplier", supplier);
            return "supplier/new";
        }
        supplierRepository.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/{id}/edit")
    public String editSupplierForm(@PathVariable Long id, Model model) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid supplier ID"));
        model.addAttribute("supplier", supplier);
        return "suppliers/edit";
    }

    @PostMapping("/{id}")
    public String updateSupplier(@PathVariable Long id, @ModelAttribute Supplier supplier) {
        supplier.setId(id);
        supplierRepository.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/{id}")
    public String showSupplier(@PathVariable Long id, Model model) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid supplier ID"));
        model.addAttribute("supplier", supplier);
        return "suppliers/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteSupplier(@PathVariable Long id) {
        supplierRepository.deleteById(id);
        return "redirect:/suppliers";
    }
}
