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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;

@Entity
@Data
public class CategoryCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ElementCollection
    @CollectionTable(name = "category_parameters")
    @MapKeyColumn(name = "parameter_name")
    @Column(name = "parameter_value")
    private Map<String, Double> parameters;
    
    @ManyToOne
    private CostCalculation costCalculation;
}