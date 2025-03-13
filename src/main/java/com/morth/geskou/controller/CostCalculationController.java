package com.morth.geskou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morth.geskou.model.CostCategory;
import com.morth.geskou.service.CostCalculationService;

@RestController
@RequestMapping("/api/cost-calculation")
@CrossOrigin(origins = "http://localhost:4200")
public class CostCalculationController {

    @Autowired
    private CostCalculationService costCalculationService;

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculateTotalCost(@RequestBody CostCategory category) {
        double totalCost = costCalculationService.calculateCategoryCost(category);
        return ResponseEntity.ok(totalCost);
    }
}

