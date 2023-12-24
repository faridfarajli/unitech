package unitech.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import unitech.service.ExchangeRatesAPIService;

@RestController
public class CurrencyRateController {

    private final ExchangeRatesAPIService exchangeRatesAPIService;

    public CurrencyRateController(ExchangeRatesAPIService exchangeRatesAPIService) {
        this.exchangeRatesAPIService = exchangeRatesAPIService;
    }


    @GetMapping("/convert-currency")
    public double convertCurrency(
            @RequestParam("amount") double amount,
            @RequestParam("baseCurrency") String baseCurrency,
            @RequestParam("targetCurrency") String targetCurrency
    ) {
        return exchangeRatesAPIService.performCurrencyConversion(amount, baseCurrency, targetCurrency);
    }
}