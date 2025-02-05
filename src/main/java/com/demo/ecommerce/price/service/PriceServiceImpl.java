package com.demo.ecommerce.price.service;

import java.util.List;

import com.demo.ecommerce.price.domain.Price;
import com.demo.ecommerce.price.dto.CommonRequestDto;
import com.demo.ecommerce.price.dto.CommonResponseDto;
import com.demo.ecommerce.price.dto.PriceRequestDto;
import com.demo.ecommerce.price.dto.PriceUserDto;
import com.demo.ecommerce.price.mapper.PriceMapper;
import com.demo.ecommerce.price.repository.PriceRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepo;

    public PriceServiceImpl(PriceRepository priceRepo) {
        this.priceRepo = priceRepo;
    }

    @Override
    public CommonResponseDto<List<PriceUserDto>> getPrices(CommonRequestDto<PriceRequestDto> priceReq) {
        log.info("Getting prices with params: {}", priceReq);
        List<Price> prices = priceRepo.getFilteredPrices(priceReq);
        return new CommonResponseDto<>(
            prices.stream().map(PriceMapper::toPriceUserDto).toList(),
            priceReq.page(),
            priceReq.offset());
    }
    
}
