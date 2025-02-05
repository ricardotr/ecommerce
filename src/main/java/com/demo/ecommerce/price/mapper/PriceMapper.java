package com.demo.ecommerce.price.mapper;

import com.demo.ecommerce.price.domain.Price;
import com.demo.ecommerce.price.dto.PriceUserDto;

public class PriceMapper {
    
    public static PriceUserDto toPriceUserDto(Price price) {
        return new PriceUserDto(
            price.getId(),
            price.getProductId(),
            price.getBrandId(),
            price.getPriceList(),
            price.getStartDate(),
            price.getEndDate(),
            price.getPrice(),
            price.getCurrency()
        );
    }
}
