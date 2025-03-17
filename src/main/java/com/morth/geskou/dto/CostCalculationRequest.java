package com.morth.geskou.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class CostCalculationRequest {
    private Long productId;
    private List<Category> categories;
    private double margeBeneficiaire;
    private double margeErreur;
    private int totalUnitesProduites;

    @Data
    public static class Category {
        private String name;
        private Map<String, Double> parameters;
    }
}

