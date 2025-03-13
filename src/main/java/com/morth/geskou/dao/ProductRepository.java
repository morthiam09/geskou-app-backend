package com.morth.geskou.dao;

import org.springframework.stereotype.Repository;

import com.morth.geskou.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
    List<Product> findAll();
    Optional<Product> findById(Integer id);
    List<Product> findByName(String name);
    Optional<Product> findByReference(String reference);
    List<Product> findByReferenceAndName(String reference, String name);
    List<Product> findByReferenceOrName(String reference, String name);
    void deleteById(Integer id);
    void deleteByReference(String reference);
    boolean existsByReference(String reference);
}
