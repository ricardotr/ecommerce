package com.demo.ecommerce.price.service;

import java.util.List;

import com.demo.ecommerce.price.dto.CommonRequestDto;
import com.demo.ecommerce.price.dto.CommonResponseDto;
import com.demo.ecommerce.price.dto.PriceRequestDto;
import com.demo.ecommerce.price.dto.PriceUserDto;

public interface PriceService {
    CommonResponseDto<List<PriceUserDto>> getPrices (CommonRequestDto<PriceRequestDto> priceReq);
}
