package com.demo.ecommerce.price.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.demo.BaseIntegrationTest;
import com.demo.ecommerce.price.domain.Price;

class PriceControllerTest extends BaseIntegrationTest {

    // MethodSource for parameterized test
    static Stream<TestCase> priceTestCases() {
        return Stream.of(
            new TestCase(
                new PriceRequest("2020-06-14T10:00:00", "35455", "1"),
                new Price(0L, 1L, convertStringToLocalDateTime("2020-06-14T00:00:00"), convertStringToLocalDateTime("2020-12-31T23:59:59"), 1, 35455L, 0L, new BigDecimal("35.50"), "EUR")
            ),
            new TestCase(
                new PriceRequest("2020-06-14T16:00:00", "35455", "1"),
                new Price(0L, 1L, convertStringToLocalDateTime("2020-06-14T15:00:00"), convertStringToLocalDateTime("2020-06-14T18:30:00"), 2, 35455L, 1L, new BigDecimal("25.45"), "EUR")
            ),
            new TestCase(
                new PriceRequest("2020-06-14T21:00:00", "35455", "1"),
                new Price(0L, 1L, convertStringToLocalDateTime("2020-06-14T00:00:00"), convertStringToLocalDateTime("2020-12-31T23:59:59"), 1, 35455L, 0L, new BigDecimal("35.50"), "EUR")
            ),
            new TestCase(
                new PriceRequest("2020-06-15T10:00:00", "35455", "1"),
                new Price(0L, 1L, convertStringToLocalDateTime("2020-06-15T00:00:00"), convertStringToLocalDateTime("2020-06-15T11:00:00"), 3, 35455L, 1L, new BigDecimal("30.50"), "EUR")
            ),
            new TestCase(
                new PriceRequest("2020-06-16T21:00:00", "35455", "1"),
                new Price(0L, 1L, convertStringToLocalDateTime("2020-06-15T16:00:00"), convertStringToLocalDateTime("2020-12-31T23:59:59"), 4, 35455L, 1L, new BigDecimal("38.95"), "EUR")
            )
        );
    }

    @ParameterizedTest
    @MethodSource("priceTestCases")
    void whenRequest_thenReturnCorrectPrice(TestCase testCase) throws Exception {
        mockMvc.perform(get("/ecommerce/v1/prices")
                .param("applicationDate", testCase.request().applicationDate())
                .param("productId", testCase.request().productId())
                .param("brandId", testCase.request().brandId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response[0].productId").value(testCase.expectedResponse.getProductId()))
                .andExpect(jsonPath("$.response[0].brandId").value(testCase.expectedResponse.getBrandId()))
                .andExpect(jsonPath("$.response[0].priceList").value(testCase.expectedResponse.getPriceList()))
                .andExpect(jsonPath("$.response[0].startDate").value(testCase.expectedResponse.getStartDate().format(FORMATTER)))
                .andExpect(jsonPath("$.response[0].endDate").value(testCase.expectedResponse.getEndDate().format(FORMATTER)))
                .andExpect(jsonPath("$.response[0].price").value(testCase.expectedResponse.getPrice().stripTrailingZeros()))
                .andExpect(jsonPath("$.response[0].currency").value(testCase.expectedResponse.getCurrency()));
                
    }

    // Inner class to hold test case data
    static record PriceRequest(String applicationDate, String productId, String brandId) {}

    // Revised TestCase class
    static record TestCase(PriceRequest request, Price expectedResponse) {}

}