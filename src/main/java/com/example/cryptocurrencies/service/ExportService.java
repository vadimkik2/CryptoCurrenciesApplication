package com.example.cryptocurrencies.service;

import java.io.Writer;
import java.util.List;

public interface ExportService {
    void writePriceToCsv(Writer writer, List<String> name);
}
