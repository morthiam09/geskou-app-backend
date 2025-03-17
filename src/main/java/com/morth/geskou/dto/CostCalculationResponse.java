package com.morth.geskou.dto;

import lombok.Data;
import java.util.Map;

@Data
public class CostCalculationResponse {
    private double totalCost;
    private double unitCost;
    private double suggestedPrice;
    private Map<String, Double> categoryCosts;
}
