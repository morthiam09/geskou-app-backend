package com.morth.geskou.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morth.geskou.model.PercentageRawMaterial;
import com.morth.geskou.service.PercentageRawMaterialService;


@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes angular
public class PercentageRawMaterialController {
    
    @Autowired
    private PercentageRawMaterialService percentageRawMaterialService;

    // Endpoint pour associer une matière première à un produit.
    @PostMapping("/{productId}/raw-materials")
    public ResponseEntity<PercentageRawMaterial> addRawMaterialToProduct(
            @PathVariable Integer productId,
            @RequestParam Integer rawMaterialId,
            @RequestParam double percentage) {

        PercentageRawMaterial percentageRawMaterial = percentageRawMaterialService.addRawMaterialToProduct(productId, rawMaterialId, percentage);
        return ResponseEntity.ok(percentageRawMaterial);
    }

    // Endpoint pour récupérer les matières premières associées à un produit.
    @GetMapping("/{productId}/raw-materials")
    public ResponseEntity<Set<PercentageRawMaterial>> getRawMaterialsForProduct(@PathVariable Integer productId) {
        Set<PercentageRawMaterial> rawMaterials = percentageRawMaterialService.getRawMaterialsForProduct(productId);
        return ResponseEntity.ok(rawMaterials);
    }

    // Endpoint pour supprimer une matière première d'un produit.
    @DeleteMapping("/{productId}/raw-materials/{rawMaterialId}")
    public ResponseEntity<Void> removeRawMaterialFromProduct(@PathVariable Integer productId, @PathVariable Integer rawMaterialId) {
        percentageRawMaterialService.removeRawMaterialFromProduct(productId, rawMaterialId);
        return ResponseEntity.noContent().build();
    }
}
