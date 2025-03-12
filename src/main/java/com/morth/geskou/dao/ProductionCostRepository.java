package com.morth.geskou.dao;

import com.morth.geskou.model.ProductionCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductionCostRepository extends JpaRepository<ProductionCost, Integer> {
    List<ProductionCost> findByProductId(Integer productId);
    List<ProductionCost> findAll();

}
