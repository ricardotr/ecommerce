package com.demo.ecommerce.price.dto;

import org.springframework.util.MultiValueMap;

public record PriceRequestDto(
    String applicationDate,
    String productId,
    String brandId,
    Integer page,
    Integer offset
) {
    public PriceRequestDto(MultiValueMap<String, String> params) {
        this(
            params.getFirst("applicationDate"),
            params.getFirst("productId"),
            params.getFirst("brandId"),
            params.containsKey("page") ? Integer.parseInt(params.getFirst("page")) : null,
            params.containsKey("offset") ? Integer.parseInt(params.getFirst("offset")) : null
        );
    }
}