package com.morth.geskou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.morth.geskou.model.RawMaterial;
import com.morth.geskou.service.RawMaterialService;

@RestController
@RequestMapping("/api/raw-materials")
@CrossOrigin(origins = "http://localhost:4200") // Autorise les requêtes angular
public class RawMaterialController {
    @Autowired
    private RawMaterialService rawMaterialService;

    @PostMapping
    public RawMaterial createRawMaterial(@RequestBody RawMaterial rawMaterial) {
        return rawMaterialService.createRawMaterial(rawMaterial);
    }

    @GetMapping
    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialService.getAllRawMaterials();
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkMaterialExists(@RequestParam("name") String name) {
        boolean exists = rawMaterialService.checkMaterialExists(name);
        return ResponseEntity.ok(exists);
    }
}
