package com.morth.geskou.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.morth.geskou.dao.CostCalculationRepository;
import com.morth.geskou.dto.CostCalculationRequest;
import com.morth.geskou.dto.CostCalculationResponse;
import com.morth.geskou.model.CostCalculation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductionCostService {

    @Autowired
    private CostCalculationRepository repository;

    public CostCalculationResponse calculateCosts(CostCalculationRequest request) {
        Map<String, Double> categoryTotals = new HashMap<>();
        double totalCost = 0.0;

        // Calcul pour chaque catégorie
        for (CostCalculationRequest.Category category : request.getCategories()) {
            double categoryTotal = calculateCategoryTotal(category);
            categoryTotals.put(category.getName(), categoryTotal);
            totalCost += categoryTotal;
        }

        // Ajout des marges
        double costWithMargin = totalCost * (1 + (request.getMargeBeneficiaire() / 100));
        double finalCost = costWithMargin * (1 + (request.getMargeErreur() / 100));
        double unitCost = finalCost / request.getTotalUnitesProduites();
        double suggestedPrice = unitCost * (1 + (request.getMargeBeneficiaire() / 100));

        // Création de l'entité pour la persistance
        CostCalculation costCalculation = createCostCalculationEntity(request, categoryTotals, finalCost, unitCost, suggestedPrice);
        repository.save(costCalculation);

        // Création de la réponse
        return createResponse(categoryTotals, finalCost, unitCost, suggestedPrice);
    }

    private double calculateCategoryTotal(CostCalculationRequest.Category category) {
        Map<String, Double> params = category.getParameters();
        switch (category.getName()) {
            case "Matières premières":
                return (params.get("quantité (kg)") * params.get("prix unitaire (€)")) 
                       + params.get("transport (€)") + params.get("pertes (€)");
            case "Main-d'œuvre":
                return (params.get("heures") * params.get("taux horaire (€)")) 
                       + params.get("charges sociales (€)");
            case "Packaging":
                return (params.get("cout Matériaux (€)") + params.get("cout Confection (€)")) 
                       * params.get("volume Emballage");
            // ... autres cas similaires pour chaque catégorie
            default:
                return params.values().stream().mapToDouble(Double::doubleValue).sum();
        }
    }

    private CostCalculation createCostCalculationEntity(CostCalculationRequest request, 
                                                      Map<String, Double> categoryTotals,
                                                      double finalCost, 
                                                      double unitCost, 
                                                      double suggestedPrice) {
        CostCalculation entity = new CostCalculation();
        entity.setProductId(request.getProductId());
        entity.setMargeBeneficiaire(request.getMargeBeneficiaire());
        entity.setMargeErreur(request.getMargeErreur());
        entity.setTotalUnitesProduites(request.getTotalUnitesProduites());
        entity.setCalculationDate(LocalDateTime.now());
        // ... configuration des autres champs
        return entity;
    }

    private CostCalculationResponse createResponse(Map<String, Double> categoryTotals, 
                                                 double finalCost, 
                                                 double unitCost, 
                                                 double suggestedPrice) {
        CostCalculationResponse response = new CostCalculationResponse();
        response.setTotalCost(finalCost);
        response.setUnitCost(unitCost);
        response.setSuggestedPrice(suggestedPrice);
        response.setCategoryCosts(categoryTotals);
        return response;
    }
}