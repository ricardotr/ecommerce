package com.demo.ecommerce.price.infrastructure.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.ecommerce.price.domain.Price;

@Repository
public interface PriceRepositoryJpa extends JpaRepository<Price, Long> {

        @Query("""
        SELECT p FROM Price p 
        JOIN ( 
            SELECT pr.brandId AS brandId, pr.productId AS productId, MAX(pr.priority) AS maxPriority 
            FROM Price pr 
            WHERE (:productId IS NULL OR pr.productId = :productId) 
            AND (:brandId IS NULL OR pr.brandId = :brandId) 
            AND (:applicationDate IS NULL OR :applicationDate BETWEEN pr.startDate AND pr.endDate) 
            GROUP BY pr.brandId, pr.productId
        ) max_p 
        ON p.brandId = max_p.brandId 
        AND p.productId = max_p.productId 
        AND p.priority = max_p.maxPriority 
        WHERE (:productId IS NULL OR p.productId = :productId) 
        AND (:brandId IS NULL OR p.brandId = :brandId) 
        AND (:applicationDate IS NULL OR :applicationDate BETWEEN p.startDate AND p.endDate)
        ORDER BY p.brandId, p.productId, p.priority ASC""")
    Page<Price> findApplicablePrices(
        @Param("productId") Long productId, 
        @Param("brandId") Long brandId, 
        @Param("applicationDate") LocalDateTime applicationDate,
        Pageable pageable
    );
}