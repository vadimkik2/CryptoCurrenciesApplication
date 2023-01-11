package com.example.cryptocurrencies.controller;

import com.example.cryptocurrencies.exception.DataProcessingException;
import com.example.cryptocurrencies.service.CurrencyService;
import com.example.cryptocurrencies.service.ExportService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cryptocurrencies")
@AllArgsConstructor
public class CurrenciesController {
    private final CurrencyService currencyService;
    private final ExportService csvExportService;

    @GetMapping("/minprice")
    public ResponseEntity<String> getMinPriceByName(@RequestParam String name) {
        return new ResponseEntity<>(currencyService.getMinPriceByName(name), HttpStatus.OK);
    }

    @GetMapping("/maxprice")
    public ResponseEntity<String> getMaxPriceByName(@RequestParam String name) {
        return new ResponseEntity<>(currencyService.getMaxPriceByName(name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCurrencyByName(@RequestParam String name,
                                                                    @RequestParam int page,
                                                                    @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "price");
        Map<String, Object> response = currencyService.findAllByNamePageable(name, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/csv")
    public void getCsv(HttpServletResponse response,
                       @RequestParam(value = "name", required = false) List<String> name) {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition","attachment; filename=\"currencies.csv\"");
        try {
            csvExportService.writePriceToCsv(response.getWriter(),name);
        } catch (IOException e) {
            throw new DataProcessingException("something goes wrong",e);
        }
    }
}
