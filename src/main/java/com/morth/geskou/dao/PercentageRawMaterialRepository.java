package com.morth.geskou.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.morth.geskou.model.PercentageRawMaterial;

@Repository
public interface PercentageRawMaterialRepository extends CrudRepository<PercentageRawMaterial, Integer>{
    List<PercentageRawMaterial> findAll();
    Optional<PercentageRawMaterial> findById(Integer id);
    Optional<PercentageRawMaterial> findByRawMaterialId(Integer id);
}
