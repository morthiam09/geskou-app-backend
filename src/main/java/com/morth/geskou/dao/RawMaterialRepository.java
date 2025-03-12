package com.morth.geskou.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.morth.geskou.model.RawMaterial;

@Repository
public interface RawMaterialRepository extends CrudRepository<RawMaterial, Integer>{
    List<RawMaterial> findAll();
    List<RawMaterial> findByName(String name);
    Optional<RawMaterial> findById(Integer id);
    boolean existsByName(String name);
}
