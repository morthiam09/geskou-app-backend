package com.morth.geskou.model;

import lombok.Data;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToOne;

@Entity
@Data
public class CalculationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double totalCost;
    private double unitCost;
    private double suggestedPrice;
    
    @ElementCollection
    @CollectionTable(name = "category_costs")
    @MapKeyColumn(name = "category_name")
    @Column(name = "cost")
    private Map<String, Double> categoryCosts;
    
    @OneToOne
    private CostCalculation costCalculation;
}
