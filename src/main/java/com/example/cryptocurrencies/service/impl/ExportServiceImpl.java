package com.example.cryptocurrencies.service.impl;

import com.example.cryptocurrencies.dto.Dto;
import com.example.cryptocurrencies.dto.PriceDto;
import com.example.cryptocurrencies.repository.CurrencyRepository;
import com.example.cryptocurrencies.service.ExportService;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;
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
    public void writePriceToCsv(Writer writer, Dto dto) {
        List<PriceDto> collect = dto.getName().stream().map(n -> creatFromRepository(n))
                .collect(Collectors.toList());

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (int i = 0; i < collect.size(); i++) {
                csvPrinter.printRecord(collect.get(i).getName(),
                        collect.get(i).getMinPrice(),collect.get(i).getMaxPrice());
            }
        } catch (IOException e) {
            log.error("CSVExportServiceImpl: failed to record csv file");
            throw new RuntimeException("Failed to record csv file");
        }
    }

    private PriceDto creatFromRepository(String name) {
        return PriceDto.builder()
                .name(name)
                .minPrice(currencyRepository.findFirstByNameOrderByPriceDesc(name).get().getPrice())
                .maxPrice(currencyRepository.findFirstByNameOrderByPriceAsc(name).get().getPrice())
                .build();
    }
}
