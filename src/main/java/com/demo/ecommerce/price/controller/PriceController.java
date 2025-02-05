package com.demo.ecommerce.price.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ecommerce.price.controller.factory.CommonRequestDtoFactory;
import com.demo.ecommerce.price.domain.Price;
import com.demo.ecommerce.price.dto.CommonRequestDto;
import com.demo.ecommerce.price.dto.PriceRequestDto;
import com.demo.ecommerce.price.service.PriceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for handling requests related to pricing.
 * 
 * Provides endpoints to retrieve pricing information based on parameters such as application date,
 * product ID, and brand ID.
 */
@Slf4j
@RestController
@RequestMapping("/ecommerce/v1/prices")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }
    
    @Operation(summary = "Get current prices based on various parameters. By default, it returns all current values, each with the highest priority, paginated in sets of 20 rows.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved price",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Price.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input parameters",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error",
            content = @Content) 
    })
    @Parameters({
        @Parameter(name = "applicationDate", in = ParameterIn.QUERY, required = false,
            description = "Application date in ISO format", example = "2023-12-14T10:00:00"),
        @Parameter(name = "productId", in = ParameterIn.QUERY, required = false,
            description = "Product ID", example = "35455"),
        @Parameter(name = "brandId", in = ParameterIn.QUERY, required = false,
            description = "Brand ID", example = "1"),
        @Parameter(name = "page", in = ParameterIn.QUERY, required = false,
            description = "Page number for pagination", example = "1"),
        @Parameter(name = "offset", in = ParameterIn.QUERY, required = false,
            description = "Offset for pagination. The default value is 20", example = "10")
    })
    @GetMapping
    public ResponseEntity<Object> getPrices(@RequestParam MultiValueMap<String, String> params) {
        log.info("Getting prices with params: {}", params);
        CommonRequestDto<PriceRequestDto> requestDto = CommonRequestDtoFactory.fromGetPricesRequestParamToDto(params);
        return ResponseEntity.ok(priceService.getPrices(requestDto));
    }
}
