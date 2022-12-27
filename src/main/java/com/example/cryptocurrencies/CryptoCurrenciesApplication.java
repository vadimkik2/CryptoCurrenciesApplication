package com.example.cryptocurrencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CryptoCurrenciesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoCurrenciesApplication.class, args);
    }

}
