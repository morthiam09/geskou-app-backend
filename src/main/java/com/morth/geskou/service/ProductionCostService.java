package com.morth.geskou.service;

import com.morth.geskou.model.ProductionCost;
import com.morth.geskou.model.Product;
import com.morth.geskou.model.PercentageRawMaterial;
import com.morth.geskou.dao.ProductionCostRepository;
import com.morth.geskou.dao.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionCostService {

    @Autowired
    private ProductionCostRepository productionCostRepository;

    @Autowired
    private ProductRepository productRepository;

    //Gère le calcul et l'enregistrement du coût de production
    public ProductionCost calculateAndSaveProductionCost(Integer productId, double laborCost,
                                                         double energyCost, double depreciationCost,
                                                         double indirectCost) {

        Product product = validateProductExists(productId);
        validateRawMaterialPercentage(product);

        double rawMaterialCost = calculateRawMaterialCost(product);
        double totalCost = calculateTotalProductionCost(rawMaterialCost, laborCost, energyCost, depreciationCost, indirectCost);
        double sellingPrice = calculateSellingPrice(totalCost, product.getProfitMargin());

        return saveProductionCost(product, rawMaterialCost, laborCost, energyCost, depreciationCost, indirectCost, sellingPrice);
    }

    //Vérifie si le produit existe
    private Product validateProductExists(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + productId));
    }


    // Vérifie si la somme des pourcentages des matières premières est bien égale à 100%.

    private void validateRawMaterialPercentage(Product product) {
        double totalPercentage = product.getPercentageRawMaterials().stream()
                .mapToDouble(PercentageRawMaterial::getPercentage)
                .sum();
        if (totalPercentage != 100) {
            throw new RuntimeException("La somme des pourcentages des matières premières doit être égale à 100%");
        }
    }

    // Calcule le coût des matières premières basé sur les pourcentages
    private double calculateRawMaterialCost(Product product) {
        return product.getPercentageRawMaterials().stream()
                .mapToDouble(prm -> (prm.getRawMaterial().getUnitPrice() * prm.getPercentage()) / 100)
                .sum();
    }


    // Calcule le coût total de production
    private double calculateTotalProductionCost(double rawMaterialCost, double laborCost, 
                                                double energyCost, double depreciationCost, double indirectCost) {
        return rawMaterialCost + laborCost + energyCost + depreciationCost + indirectCost;
    }

    // Calcule le prix de vente recommandé
    private double calculateSellingPrice(double totalCost, double profitMargin) {
        return totalCost * (1 + profitMargin / 100);
    }

    // Crée et enregistre un coût de production

    private ProductionCost saveProductionCost(Product product, double rawMaterialCost, 
                                              double laborCost, double energyCost, 
                                              double depreciationCost, double indirectCost, 
                                              double sellingPrice) {

        ProductionCost productionCost = new ProductionCost(product, rawMaterialCost, laborCost,
                energyCost, depreciationCost, indirectCost, product.getProfitMargin());

        productionCostRepository.save(productionCost);

        // Mise à jour du produit
        product.setProductionCost(productionCost.getTotalCost());
        product.setRecommendedSellingPrice(sellingPrice);
        productRepository.save(product);

        return productionCost;
    }

    // Récupérer l'historique des coûts de production d'un produit

    public List<ProductionCost> getProductionCostHistory(Integer productId) {
        return productionCostRepository.findByProductId(productId);
    }

    //Récupérer tout les historiques des coûts de production
    public List<ProductionCost> getAllProductionCostHistory() {
        return productionCostRepository.findAll();
    }
}
