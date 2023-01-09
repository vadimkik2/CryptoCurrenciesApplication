package com.example.cryptocurrencies.service.impl;

import com.example.cryptocurrencies.dto.CurrencyListDto;
import com.example.cryptocurrencies.dto.PriceDto;
import com.example.cryptocurrencies.repository.CurrencyRepository;
import com.example.cryptocurrencies.service.ExportService;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
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
    public void writePriceToCsv(Writer writer, CurrencyListDto dto) {
        List<PriceDto> collect = dto.getName().stream().map(this::creatFromRepository)
                .toList();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (PriceDto priceDto : collect) {
                csvPrinter.printRecord(priceDto.getName(),
                        priceDto.getMinPrice(), priceDto.getMaxPrice());
            }
        } catch (IOException e) {
            log.error("CSVExportServiceImpl: failed to record csv file");
            throw new RuntimeException("Failed to record csv file");
        }
    }

    private PriceDto creatFromRepository(String name) {
        if (currencyRepository.findFirstByNameOrderByPriceDesc(name).isEmpty()
                || currencyRepository.findFirstByNameOrderByPriceAsc(name).isEmpty()) {
            throw new RuntimeException("Currency name is invalid or "
                   + "this currency is not in the database");
        }
        return PriceDto.builder()
                .name(name)
                .minPrice(currencyRepository.findFirstByNameOrderByPriceDesc(name).get().getPrice())
                .maxPrice(currencyRepository.findFirstByNameOrderByPriceAsc(name).get().getPrice())
                .build();
    }
}
