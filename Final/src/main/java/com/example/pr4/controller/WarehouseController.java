package com.example.pr4.controller;

import com.example.pr4.models.Warehouse;
import com.example.pr4.repo.ProductRepository;
import com.example.pr4.repo.WarehouseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping()
    public String index(Model model) {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        model.addAttribute("warehouses", warehouses);
        return "Warehouses/index";
    }

    @GetMapping("/new")
    public String newWarehouseForm(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        model.addAttribute("products", productRepository.findAll());
        return "Warehouses/new";
    }

    @PostMapping
    public String createWarehouse(@Valid @ModelAttribute("warehouse") Warehouse warehouse, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("warehouse", warehouse);
            return "Warehouses/new";
        }
        warehouseRepository.save(warehouse);
        return "redirect:/Warehouses";
    }

    @GetMapping("/{id}/edit")
    public String editWarehouseForm(@PathVariable Long id, Model model) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse ID"));
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("products", productRepository.findAll());
        return "Warehouses/edit";
    }

    @PostMapping("/{id}")
    public String updateWarehouse(@PathVariable Long id, @ModelAttribute Warehouse warehouse) {
        warehouse.setId(id);
        warehouseRepository.save(warehouse);
        return "redirect:/Warehouses";
    }

    @GetMapping("/{id}")
    public String showWarehouse(@PathVariable Long id, Model model) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid warehouse ID"));
        model.addAttribute("warehouse", warehouse);
        return "Warehouses/show";
    }

    @GetMapping("/{id}/delete")
    public String deleteWarehouse(@PathVariable Long id) {
        warehouseRepository.deleteById(id);
        return "redirect:/Warehouses";
    }
}