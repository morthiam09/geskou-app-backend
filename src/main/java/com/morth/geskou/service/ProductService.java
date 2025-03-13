package com.morth.geskou.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.morth.geskou.dao.ProductRepository;
import com.morth.geskou.dao.RawMaterialRepository;
import com.morth.geskou.model.PercentageRawMaterial;
import com.morth.geskou.model.Product;
import com.morth.geskou.model.RawMaterial;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository; // Injection
    @Autowired
    private RawMaterialRepository rawMaterialRepository; // 🔹 Ajout du repository des matières premières

    // Créer un produit
    @Transactional
    public Product createProduct(Product product) {
        // 🔹 Associer chaque matière première existante à l'objet PercentageRawMaterial
        for (PercentageRawMaterial prm : product.getPercentageRawMaterials()) {
            RawMaterial existingRawMaterial = rawMaterialRepository.findById(prm.getRawMaterial().getId())
                    .orElseThrow(() -> new RuntimeException(
                            "Matière première introuvable avec l'ID: " + prm.getRawMaterial().getId()));

            prm.setRawMaterial(existingRawMaterial); // 🔹 Associe la matière première existante
        }

        return productRepository.save(product); // 🔹 Sauvegarde avec les matières premières bien définies
    }

    // Trouver tous les produits
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Trouver un produit par son id
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    // trouver un produit par son nom
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    // Trouver un produit par sa référence
    public Optional<Product> getProductByReference(String reference) {
        return productRepository.findByReference(reference);
    }

    // Trouver un produit par sa référence et son nom
    public List<Product> getProductByReferenceAndName(String reference, String name) {
        return productRepository.findByReferenceAndName(reference, name);
    }

    // Trouver un produit par sa référence ou son nom
    public List<Product> getProductByReferenceOrName(String reference, String name) {
        return productRepository.findByReferenceOrName(reference, name);
    }

    // Mettre à jour un produit
    public Product updateProduct(Integer id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setReference(updatedProduct.getReference());
            product.setName(updatedProduct.getName());
            product.setProductionCost(updatedProduct.getProductionCost());
            product.setRecommendedSellingPrice(updatedProduct.getRecommendedSellingPrice());
            product.setProfitMargin(updatedProduct.getProfitMargin());
            product.setPercentageRawMaterials(updatedProduct.getPercentageRawMaterials());
            product.setProductionCostHistory(updatedProduct.getProductionCostHistory());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + id));
    }

    // Supprimer un produit
    public void deleteProductById(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Produit non trouvé avec l'ID: " + id);
        }
        productRepository.deleteById(id);
    }

    // Supprimer un produit
    public void deleteProductByReference(String reference) {
        if (!productRepository.existsByReference(reference)) {
            throw new RuntimeException("Produit non trouvé avec l'ID: " + reference);
        }
        productRepository.deleteByReference(reference);
    }
}
