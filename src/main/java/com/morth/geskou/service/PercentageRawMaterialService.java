package com.morth.geskou.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morth.geskou.dao.ProductRepository;
import com.morth.geskou.dao.RawMaterialRepository;
import com.morth.geskou.model.PercentageRawMaterial;
import com.morth.geskou.model.Product;
import com.morth.geskou.model.RawMaterial;

import jakarta.transaction.Transactional;

@Service
public class PercentageRawMaterialService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    // Associe une matière première à un produit avec un pourcentage donné.
    @Transactional
    public PercentageRawMaterial addRawMaterialToProduct(Integer productId, Integer rawMaterialId, double percentage) {
        // Vérifie si le produit existe
        Product product = productRepository.findById(productId)
                            .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + productId));
        
        // Vérifie si la matière première existe
        RawMaterial rawMaterial = rawMaterialRepository.findById(rawMaterialId)
                            .orElseThrow(() -> new RuntimeException("Matière première non trouvé avec l'ID : " + rawMaterialId));

        // Récupérer toutes les associations existantes pour vérifier les pourcentages
        Set<PercentageRawMaterial> existingMaterials = product.getPercentageRawMaterials();

        // Vérifier que la somme des pourcentages ne dépasse pas 100%
        double currentTotalPercentage = existingMaterials.stream()
                .mapToDouble(PercentageRawMaterial::getPercentage)
                .sum();

        if (currentTotalPercentage + percentage > 100) {
            throw new RuntimeException("Le total des pourcentages des matières premières ne peut pas dépasser 100%");
        }
        
        // Créer l'association entre le produit et la matière première
        PercentageRawMaterial percentageRawMaterial = new PercentageRawMaterial(rawMaterial, percentage);
        product.getPercentageRawMaterials().add(percentageRawMaterial);
        productRepository.save(product);
        return percentageRawMaterial;
    }

    // Récupère toutes les matières premières associées à un produit.
    public Set<PercentageRawMaterial> getRawMaterialsForProduct( Integer id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + id));

        return product.getPercentageRawMaterials();
    }

    //Supprime une matière première associée à un produit.
    @Transactional
    public void removeRawMaterialFromProduct(Integer productId, Integer rawMaterialId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID : " + productId));

        // Trouver la matière première associée dans la liste
        product.getPercentageRawMaterials().removeIf(prm -> prm.getRawMaterial().getId().equals(rawMaterialId));

        // Sauvegarder le produit mis à jour
        productRepository.save(product);
    }
}
