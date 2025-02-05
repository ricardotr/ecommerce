package com.demo.ecommerce.price.infrastructure.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.demo.ecommerce.price.domain.Price;
import com.demo.ecommerce.price.dto.CommonRequestDto;
import com.demo.ecommerce.price.dto.PriceRequestDto;
import com.demo.ecommerce.price.infrastructure.repository.PriceRepositoryJpa;
import com.demo.ecommerce.price.repository.PriceRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public final class PriceRepositoryDAO implements PriceRepository {

    private final PriceRepositoryJpa priceRepository;

    public PriceRepositoryDAO(PriceRepositoryJpa priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> getFilteredPrices(CommonRequestDto<PriceRequestDto> commonRequestDto) {
        log.info("Getting prices with params: {}", commonRequestDto);
        Pageable pageable = PageRequest.of(commonRequestDto.page(), commonRequestDto.offset());
        return priceRepository.findApplicablePrices(
                commonRequestDto.request().productId(), 
                commonRequestDto.request().brandId(),
                commonRequestDto.request().applicationDate(), 
                pageable).
                toList();
    }
    
}
