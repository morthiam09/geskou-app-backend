package com.morth.geskou.security.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.morth.geskou.security.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);

}
