package com.example.cryptocurrencies.sheduler;

import com.example.cryptocurrencies.dto.CurrencyDto;
import com.example.cryptocurrencies.service.impl.CurrencyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ScheduledCurrency {
    private final String BTC = "BTC/USD";
    private final String XRP = "XRP/USD";
    private final String ETH = "ETH/USD";
    @Value("${value}")
    private String url;
    private final RestTemplate restTemplate;
    private final CurrencyServiceImpl currencyService;

    public ScheduledCurrency(RestTemplate restTemplate,
                             CurrencyServiceImpl currencyService) {
        this.restTemplate = restTemplate;
        this.currencyService = currencyService;
    }

    @Scheduled(cron = "*/30  * * * * *")
    public void saveDataFromApi() {
        currencyService.save(getLastPrice(BTC));
        currencyService.save(getLastPrice(ETH));
        currencyService.save(getLastPrice(XRP));
    }

    public CurrencyDto getLastPrice(String currency) {
        return restTemplate.getForObject(url + currency, CurrencyDto.class);
    }
}
