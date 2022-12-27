package com.example.cryptocurrencies.service.impl;

import com.example.cryptocurrencies.dto.PriceDto;
import com.example.cryptocurrencies.repository.CurrencyRepository;
import com.example.cryptocurrencies.service.ExportService;
import java.io.IOException;
import java.io.Writer;
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
    public void writePriceToCsv(Writer writer) {
        PriceDto btc = creatFromRepository("BTC");
        PriceDto xrp = creatFromRepository("XRP");
        PriceDto eth = creatFromRepository("ETH");

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord(btc.getName(),btc.getMinPrice(),btc.getMaxPrice());
            csvPrinter.printRecord(eth.getName(),eth.getMinPrice(),eth.getMaxPrice());
            csvPrinter.printRecord(xrp.getName(),xrp.getMinPrice(),xrp.getMaxPrice());

        } catch (IOException e) {
            log.error("CSVExportServiceImpl: Error while writing csv");
            throw new RuntimeException("Error while writing csv");
        }
    }

    private PriceDto creatFromRepository(String name) {
        return PriceDto.builder()
                .name(name)
                .minPrice(currencyRepository.findFirstByNameOrderByPriceDesc(name).getPrice())
                .maxPrice(currencyRepository.findFirstByNameOrderByPriceAsc(name).getPrice())
                .build();
    }
}
