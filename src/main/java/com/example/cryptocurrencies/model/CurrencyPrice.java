package com.example.cryptocurrencies.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class CurrencyPrice {
    private String id;
    private String name;
    private BigDecimal price;
}
