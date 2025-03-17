package com.morth.geskou.model;

import lombok.Data;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class CostCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long productId;
    private double margeBeneficiaire;
    private double margeErreur;
    private int totalUnitesProduites;
    private LocalDateTime calculationDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "costCalculation")
    private List<CategoryCalculation> categories;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "costCalculation")
    private CalculationResult result;
}