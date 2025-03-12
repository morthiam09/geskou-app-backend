package com.morth.geskou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morth.geskou.model.ProductionCost;
import com.morth.geskou.service.ProductionCostService;

@RestController
@RequestMapping("/api/production-costs")
@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes angular
public class ProductionCostController {
    @Autowired
    private ProductionCostService productionCostService;

    // Endpoint pour calculer et enregistrer un coût de production.
    @PostMapping("/calculate/{productId}")
    public ResponseEntity<ProductionCost> calculateProductionCost(  @PathVariable Integer productId,
                                                                    @RequestParam double laborCost,
                                                                    @RequestParam double energyCost,
                                                                    @RequestParam double depreciationCost,
                                                                    @RequestParam double indirectCost) {
        ProductionCost productionCost = productionCostService.calculateAndSaveProductionCost( productId, 
                                                                                              laborCost, 
                                                                                              energyCost,
                                                                                              depreciationCost, 
                                                                                              indirectCost);
        
        return ResponseEntity.ok(productionCost);
    }

    // Endpoint pour récupérer l'historique des coûts de production d'un produit.
    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductionCost>> getProductionCostHistory(@PathVariable Integer productId) {
        List<ProductionCost> productionCosts = productionCostService.getProductionCostHistory(productId);
        return ResponseEntity.ok(productionCosts);
    }

}
