package com.example.cryptocurrencies.mapper;

import com.example.cryptocurrencies.dto.CurrencyDto;
import com.example.cryptocurrencies.model.CurrencyPrice;
import org.springframework.stereotype.Component;

@Component
public class CurrencyMapperImpl implements CurrencyMapper {

    @Override
    public CurrencyPrice priceDtoToCurrencyPrice(CurrencyDto priceDto) {
        return CurrencyPrice.builder().price(priceDto.getPrice())
                .name(priceDto.getFirstCurrency()).build();
    }
}
