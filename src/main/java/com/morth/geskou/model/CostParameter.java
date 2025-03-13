package com.morth.geskou.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CostParameter {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "cost_category_id", nullable = false)
    private CostCategory costCategory;
    
    private String name;  // Exemple: "Quantité utilisée"
    private double value; // Valeur saisie
    public CostParameter(CostCategory costCategory, String name, double value) {
        this.costCategory = costCategory;
        this.name = name;
        this.value = value;
    }
    public CostParameter() {
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public CostCategory getCostCategory() {
        return costCategory;
    }
    public void setCostCategory(CostCategory costCategory) {
        this.costCategory = costCategory;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "CostParameter [id=" + id + ", costCategory=" + costCategory + ", name=" + name + ", value=" + value
                + "]";
    }
}

