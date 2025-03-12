package com.morth.geskou.config;

import com.morth.geskou.dao.*;
import com.morth.geskou.service.ProductionCostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Initializer {

    @Bean
    CommandLineRunner initDatabase(
            RawMaterialRepository rawMaterialRepository,
            ProductRepository productRepository,
            PercentageRawMaterialRepository productRawMaterialRepository,
            ProductionCostRepository productionCostRepository,
            ProductionCostService productionCostService) {
        
        return args -> {

        
        };
    }
}
