package com.morth.geskou.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CostCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "costCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CostParameter> parameters = new ArrayList<>();

    public CostCategory(String name) {
        this.name = name;
    }

    public CostCategory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CostParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<CostParameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "CostCategory [id=" + id + ", name=" + name + ", parameters=" + parameters + "]";
    }

}

