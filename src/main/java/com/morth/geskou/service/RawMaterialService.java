package com.morth.geskou.service;

import org.springframework.stereotype.Service;
import com.morth.geskou.dao.RawMaterialRepository;
import com.morth.geskou.model.RawMaterial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RawMaterialService {
    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    public RawMaterial createRawMaterial(RawMaterial rawMaterial) {
        return rawMaterialRepository.save(rawMaterial);
    }

    public List<RawMaterial> getAllRawMaterials() {
        return rawMaterialRepository.findAll();
    }

    // Vérifier si une matière existe déjà
    public Boolean checkMaterialExists(String name) {
        return rawMaterialRepository.existsByName(name);
    }
}
