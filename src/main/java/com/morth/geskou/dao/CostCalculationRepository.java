package com.morth.geskou.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.morth.geskou.model.CostCalculation;

public interface CostCalculationRepository extends JpaRepository<CostCalculation, Long> {
}
