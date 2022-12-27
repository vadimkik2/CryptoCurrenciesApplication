package com.example.cryptocurrencies.dto;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceDto {
    private String name;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
}
