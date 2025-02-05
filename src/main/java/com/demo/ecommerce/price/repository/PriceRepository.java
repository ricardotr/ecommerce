package com.demo.ecommerce.price.repository;

import java.util.List;

import com.demo.ecommerce.price.domain.Price;
import com.demo.ecommerce.price.dto.CommonRequestDto;
import com.demo.ecommerce.price.dto.PriceRequestDto;

public interface PriceRepository {
    List<Price> getFilteredPrices(CommonRequestDto<PriceRequestDto> commonRequestDto);
}
