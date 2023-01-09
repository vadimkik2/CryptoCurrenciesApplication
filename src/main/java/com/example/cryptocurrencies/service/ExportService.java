package com.example.cryptocurrencies.service;

import com.example.cryptocurrencies.dto.CurrencyListDto;
import java.io.Writer;

public interface ExportService {
    void writePriceToCsv(Writer writer, CurrencyListDto dto);
}
