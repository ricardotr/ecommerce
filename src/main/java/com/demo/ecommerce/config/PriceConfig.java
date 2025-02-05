package com.demo.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.ecommerce.price.repository.PriceRepository;
import com.demo.ecommerce.price.service.PriceServiceImpl;

@Configuration
public class PriceConfig {
    
    @Bean
    PriceServiceImpl priceService(PriceRepository priceRepo) {
        return new PriceServiceImpl(priceRepo);
    }
}
