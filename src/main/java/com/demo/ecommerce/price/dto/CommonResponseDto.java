package com.demo.ecommerce.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommonResponseDto<T>(
    @JsonProperty("response") T response,
    @JsonProperty("page") Integer page,
    @JsonProperty("offset") Integer offset){}
