package com.example.cryptocurrencies.service.impl;

import com.example.cryptocurrencies.dto.CurrencyDto;
import com.example.cryptocurrencies.exception.CustomException;
import com.example.cryptocurrencies.mapper.CurrencyMapper;
import com.example.cryptocurrencies.model.CurrencyPrice;
import com.example.cryptocurrencies.repository.CurrencyRepository;
import com.example.cryptocurrencies.service.CurrencyService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
        Optional<CurrencyPrice> orderByPriceAsc = currencyRepository
                .findFirstByNameOrderByPriceAsc(name);
        if (orderByPriceAsc.isEmpty()) {
            throw new CustomException("Currency name is invalid or "
                    + "this currency is not in the database");
        }
        return orderByPriceAsc.get().getPrice().toString();
    }

    @Override
    public String getMaxPriceByName(String name) {
        Optional<CurrencyPrice> firstByNameOrderByPriceDesc = currencyRepository
                .findFirstByNameOrderByPriceDesc(name);
        if (firstByNameOrderByPriceDesc.isEmpty()) {
            throw new CustomException("Currency name is invalid or "
                    + "this currency is not in the database");
        }
        return firstByNameOrderByPriceDesc.get().getPrice().toString();
    }

    @Override
    public Map<String, Object> findAllByNamePageable(String name, Pageable pageable) {
        Map<String,Object> result = new HashMap<>();
        Page<CurrencyPrice> page = currencyRepository.findAllByName(name,pageable);
        if (page == null) {
            throw new CustomException("Currency name is invalid or "
                    + "this currency is not in the database");
        }
        result.put("Currencies",page.getContent());
        result.put("CurrentPage",page.getNumber());
        result.put("TotalItems",page.getTotalElements());
        result.put("Pages",page.getTotalPages());
        return result;
    }
}
