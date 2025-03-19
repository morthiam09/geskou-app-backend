package com.morth.geskou.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.morth.geskou.security.models.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByName(String name);

}
