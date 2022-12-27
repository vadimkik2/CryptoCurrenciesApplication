package com.example.cryptocurrencies.service;

import java.io.Writer;

public interface ExportService {
    void writePriceToCsv(Writer writer);
}
