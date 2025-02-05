package com.demo.ecommerce.price.controller.factory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.springframework.util.MultiValueMap;

import com.demo.ecommerce.price.dto.CommonRequestDto;
import com.demo.ecommerce.price.dto.PriceRequestDto;

public final class CommonRequestDtoFactory<T>{

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public static <T> CommonRequestDto<T> fromRequestParamToDto(T in, MultiValueMap<String, String> params){
        return new CommonRequestDto<T>(
            in, 
            getIntParam(params, "page", 0),
            getIntParam(params, "offset", 20)
        );
    }
    
    public static CommonRequestDto<PriceRequestDto> fromGetPricesRequestParamToDto(MultiValueMap<String, String> params){
        return fromRequestParamToDto(new PriceRequestDto(
                    getLocalDateTimeParam(params, "applicationDate", LocalDateTime.now()),
                    getLongParam(params, "productId"),
                    getLongParam(params, "brandId")),
                    params);
    }
    /**
     * Safely retrieves an localDateTime request parameter or returns a default value.
     */
    private static LocalDateTime getLocalDateTimeParam(MultiValueMap<String, String> params, String key, LocalDateTime defaultValue) {
        return Optional.ofNullable(params.getFirst(key))
            .map(value -> {
                try {
                    return LocalDateTime.parse(value, FORMATTER);
                } catch (DateTimeParseException e) {
                    return defaultValue;
                }
            })
            .orElse(defaultValue);
    }
    /**
     * Safely retrieves an long request parameter or returns a default value.
     */
    private static Long getLongParam(MultiValueMap<String, String> params, String key) {
        Long response = null;
        if (params.containsKey(key)) {
            try {
                response = Long.valueOf(params.getFirst(key));
            } catch (NumberFormatException e) {
                return response;
            }
        }
        return response;
    }
    
    /**
     * Safely retrieves an integer request parameter or returns a default value.
     */
    private static int getIntParam(MultiValueMap<String, String> params, String key, int defaultValue) {
        return Optional.ofNullable(params.getFirst(key))
            .map(value -> {
                try {
                    return Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    return defaultValue;
                }
            })
            .orElse(defaultValue);
    }
}
