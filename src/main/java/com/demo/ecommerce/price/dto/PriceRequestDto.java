package com.demo.ecommerce.price.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PriceRequestDto(
    @JsonProperty("applicationDate") LocalDateTime applicationDate,
    @JsonProperty("productId") Long productId,
    @JsonProperty("brandId") Long brandId){}