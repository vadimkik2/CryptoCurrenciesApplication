package com.example.cryptocurrencies.service;

import com.example.cryptocurrencies.dto.CurrencyDto;
import java.util.Map;
import org.springframework.data.domain.Pageable;

public interface CurrencyService {
    void save(CurrencyDto priceDto);

    String getMinPriceByName(String name);

    String getMaxPriceByName(String name);

    Map<String, Object> findAllByNamePageable(String name, Pageable pageable);
}
