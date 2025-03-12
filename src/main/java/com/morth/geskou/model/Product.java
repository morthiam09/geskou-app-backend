package com.morth.geskou.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String reference;

    private String name;

    // Utilisation de Set pour éviter les doublons de matières premières
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // relation Unidirectionnelle
    private Set<PercentageRawMaterial> percetageRawMaterials = new HashSet<>();

    private double productionCost;
    private double recommendedSellingPrice;
    private double profitMargin;    

    // Utilisation de List pour garder l'ordre chronologique des coûts de production
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true) // relation bidirectionnelle
    private List<ProductionCost> productionCostHistory = new ArrayList<>();

    public Product() {
    }

    public Product(String reference, String name, double profitMargin) {
        this.reference = reference;
        this.name = name;
        this.profitMargin = profitMargin;
        this.productionCostHistory = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PercentageRawMaterial> getPercentageRawMaterials() {
        return percetageRawMaterials;
    }

    public void setPercentageRawMaterials(Set<PercentageRawMaterial> rawMaterials) {
        this.percetageRawMaterials = rawMaterials;
    }

    public double getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(double productionCost) {
        this.productionCost = productionCost;
    }

    public double getRecommendedSellingPrice() {
        return recommendedSellingPrice;
    }

    public void setRecommendedSellingPrice(double recommendedSellingPrice) {
        this.recommendedSellingPrice = recommendedSellingPrice;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public List<ProductionCost> getProductionCostHistory() {
        return productionCostHistory;
    }

    public void setProductionCostHistory(List<ProductionCost> productionCostHistory) {
        this.productionCostHistory = productionCostHistory;
    }

    // Vérifie que la somme des pourcentages des matières premières est bien égale à 100%.

    public boolean isRawMaterialPercentageValid() {
        double totalPercentage = percetageRawMaterials.stream()
                .mapToDouble(PercentageRawMaterial::getPercentage)
                .sum();
        return totalPercentage == 100.0;
    }

    @Override
    public String toString() {
        return "Product [ id=" + id + ", reference=" + reference + ", name=" + name +
               ", productionCost=" + productionCost + ", recommendedSellingPrice=" + recommendedSellingPrice +
               ", profitMargin=" + profitMargin + " ]";
    }
}
