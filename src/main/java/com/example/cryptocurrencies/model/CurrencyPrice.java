package com.example.cryptocurrencies.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
@Builder
public class CurrencyPrice {
    private String id;
    private String name;
    private BigDecimal price;
}
