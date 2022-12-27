package com.example.cryptocurrencies.mapper;

import com.example.cryptocurrencies.dto.CurrencyDto;
import com.example.cryptocurrencies.model.CurrencyPrice;

public interface CurrencyMapper {
    CurrencyPrice priceDtoToCurrencyPrice(CurrencyDto priceDto);
}
