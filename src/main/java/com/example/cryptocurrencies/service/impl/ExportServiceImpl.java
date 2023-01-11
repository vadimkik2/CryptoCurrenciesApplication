package com.example.cryptocurrencies.service.impl;

import com.example.cryptocurrencies.dto.PriceDto;
import com.example.cryptocurrencies.exception.CustomException;
import com.example.cryptocurrencies.exception.DataProcessingException;
import com.example.cryptocurrencies.model.CurrencyPrice;
import com.example.cryptocurrencies.repository.CurrencyRepository;
import com.example.cryptocurrencies.service.ExportService;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ExportServiceImpl implements ExportService {
    private final CurrencyRepository currencyRepository;

    @Override
    public void writePriceToCsv(Writer writer, List<String> name) {
        List<PriceDto> collect = name.stream().map(this::creatFromRepository)
                .toList();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (PriceDto priceDto : collect) {
                csvPrinter.printRecord(priceDto.getName(),
                        priceDto.getMinPrice(), priceDto.getMaxPrice());
            }
        } catch (IOException e) {
            log.error("CSVExportServiceImpl: failed to record csv file");
            throw new DataProcessingException("Failed to record csv file for currencies: "
                    + name, e);
        }
    }

    private PriceDto creatFromRepository(String name) {
        Optional<CurrencyPrice> minPrice = currencyRepository.findFirstByNameOrderByPriceDesc(name);
        Optional<CurrencyPrice> maxPrice = currencyRepository.findFirstByNameOrderByPriceAsc(name);
        if (minPrice.isEmpty() || maxPrice.isEmpty()) {
            throw new CustomException("Currency name is invalid or "
                    + "this currency is not in the database");
        }
        return PriceDto.builder()
                .name(name)
                .minPrice(minPrice.get().getPrice())
                .maxPrice(maxPrice.get().getPrice())
                .build();
    }
}
