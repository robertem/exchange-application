package pe.robertem.example.exchangeapplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.robertem.example.exchangeapplication.entity.ExchangeRate;
import pe.robertem.example.exchangeapplication.service.ExchangeRateService;

import java.util.List;

@RestController
@RequestMapping("/exchangeRates")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @PostMapping
    public ExchangeRate create(@RequestBody ExchangeRate exchangeRate) {
        return exchangeRateService.create(exchangeRate);
    }

    @GetMapping
    public List<ExchangeRate> findAll(@RequestParam(name = "currency", required = false) String currency) {
        return exchangeRateService.findByCurrency(currency);
    }

}
