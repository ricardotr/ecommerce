package com.demo.ecommerce.price.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the pricing details for products within a specific brand.
 * 
 * <p>This entity maps to the "PRICES" table and includes information
 * about applicable price lists, priorities, and validity periods.
 * 
 * <p>The {@link lombok.NoArgsConstructor} annotation is required for
 * Jackson deserialization, ensuring compatibility with JSON data structures. Additionally,
 * {@link lombok.Data} is used instead of {@link lombok.Value} or Java {@code record},
 * because JPA entity classes require mutability for ORM operations.
 */
@Entity
@Table(name = "PRICES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "BRAND_ID", nullable = false)
    private Long brandId;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PRICE_LIST", nullable = false)
    private Integer priceList;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "PRIORITY", length = 1, nullable = false)
    private Long priority;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "CURR", length = 3, nullable = false)
    private String currency;
}
