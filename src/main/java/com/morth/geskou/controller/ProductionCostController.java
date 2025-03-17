package com.morth.geskou.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.morth.geskou.dto.CostCalculationRequest;
import com.morth.geskou.dto.CostCalculationResponse;
import com.morth.geskou.service.ProductionCostService;

@RestController
@RequestMapping("/api/production-costs")
@CrossOrigin(origins = "*")
public class ProductionCostController {

    @Autowired
    private ProductionCostService productionCostService;

    @PostMapping
    public ResponseEntity<CostCalculationResponse> calculateCosts(@RequestBody CostCalculationRequest request) {
        CostCalculationResponse response = productionCostService.calculateCosts(request);
        return ResponseEntity.ok(response);
    }
}

