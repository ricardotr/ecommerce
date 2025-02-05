package com.demo.ecommerce.price.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CommonRequestDto<T>(
    @JsonProperty("request") T request,
    @JsonProperty("page") Integer page,
    @JsonProperty("offset") Integer offset){}
