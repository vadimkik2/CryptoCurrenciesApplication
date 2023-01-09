package com.example.cryptocurrencies.service;

import com.example.cryptocurrencies.dto.Dto;
import java.io.Writer;

public interface ExportService {
    void writePriceToCsv(Writer writer, Dto dto);
}
