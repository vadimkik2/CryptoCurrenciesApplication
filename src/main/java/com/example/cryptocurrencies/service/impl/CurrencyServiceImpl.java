package com.example.cryptocurrencies.service.impl;

import com.example.cryptocurrencies.dto.CurrencyDto;
import com.example.cryptocurrencies.mapper.CurrencyMapper;
import com.example.cryptocurrencies.model.CurrencyPrice;
import com.example.cryptocurrencies.repository.CurrencyRepository;
import com.example.cryptocurrencies.service.CurrencyService;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;

    @Override
    public void save(CurrencyDto priceDto) {
        currencyRepository.save(currencyMapper.priceDtoToCurrencyPrice(priceDto));
    }

    @Override
    public String getMinPriceByName(String name) {
        CurrencyPrice firstByNameOrderByPriceAsc = currencyRepository
                .findFirstByNameOrderByPriceAsc(name);
        if (firstByNameOrderByPriceAsc == null) {
            throw new RuntimeException("Currency name is invalid");
        }
        return firstByNameOrderByPriceAsc.getPrice().toString();
    }

    @Override
    public String getMaxPriceByName(String name) {
        CurrencyPrice firstByNameOrderByPriceDesc = currencyRepository
                .findFirstByNameOrderByPriceDesc(name);
        if (firstByNameOrderByPriceDesc == null) {
            throw new RuntimeException("Currency name is invalid");
        }
        return firstByNameOrderByPriceDesc.getPrice().toString();
    }

    @Override
    public Map<String, Object> findAllByNamePageable(String name, Pageable pageable) {
        Map<String,Object> result = new HashMap<>();
        Page<CurrencyPrice> page = currencyRepository.findAllByName(name,pageable);
        if (page == null) {
            throw new RuntimeException("Currency name is invalid");
        }
        result.put("Currencies",page.getContent());
        result.put("CurrentPage",page.getNumber());
        result.put("totalItems:",page.getTotalElements());
        result.put("Pages:",page.getTotalPages());
        return result;
    }
}
