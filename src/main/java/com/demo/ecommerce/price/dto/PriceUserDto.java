package com.demo.ecommerce.price.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PriceUserDto(
    @JsonProperty("id") Long id,
    @JsonProperty("productId") Long productId,
    @JsonProperty("brandId") Long brandId,
    @JsonProperty("priceList") Integer priceList,
    @JsonProperty("startDate") LocalDateTime startDate,
    @JsonProperty("endDate") LocalDateTime endDate,
    @JsonProperty("price") BigDecimal price,
    @JsonProperty("currency") String currency){}
