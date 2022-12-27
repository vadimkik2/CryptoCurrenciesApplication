package com.example.cryptocurrencies.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyDto {
    private BigDecimal price;
    private String firstCurrency;
    private String secondCurrency;
}
