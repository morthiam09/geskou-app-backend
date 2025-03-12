package com.morth.geskou.model;

import jakarta.persistence.*;
import java.time.LocalDate;;

@Entity
public class ProductionCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private double rawMaterialCost; // Coût des matières premières
    private double laborCost; // Coût de la main-d'œuvre
    private double energyCost; // Coût de l’énergie
    private double depreciationCost; // Amortissement des équipements
    private double indirectCost; // Coût des frais indirects
    private double totalCost; // Coût total de production
    private double margin; // Marge bénéficiaire en %
    private double recommendedSellingPrice; // Prix de vente recommandé

    private LocalDate calculationDate; // Date du calcul

    public ProductionCost() {
    }

    public ProductionCost(Product product, double rawMaterialCost, double laborCost, double energyCost,
                          double depreciationCost, double indirectCost, double margin) {
        this.product = product;
        this.rawMaterialCost = rawMaterialCost;
        this.laborCost = laborCost;
        this.energyCost = energyCost;
        this.depreciationCost = depreciationCost;
        this.indirectCost = indirectCost;
        this.totalCost = rawMaterialCost + laborCost + energyCost + depreciationCost + indirectCost;
        this.margin = margin;
        this.recommendedSellingPrice = this.totalCost * (1 + margin / 100);
        this.calculationDate = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getSellingPrice() {
        return recommendedSellingPrice;
    }

    public LocalDate getCalculationDate() {
        return calculationDate;
    }

    @Override
    public String toString() {
        return "ProductionCost [id=" + id + ", totalCost=" + totalCost + ", recommenedS ellingPrice=" + recommendedSellingPrice +
               ", calculationDate=" + calculationDate + "]";
    }
}
