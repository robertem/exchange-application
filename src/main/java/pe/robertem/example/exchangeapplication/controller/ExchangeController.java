package pe.robertem.example.exchangeapplication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.robertem.example.exchangeapplication.entity.Exchange;
import pe.robertem.example.exchangeapplication.service.ExchangeService;

@RestController
@RequestMapping("/exchanges")
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @PostMapping
    public Exchange create(@RequestBody Exchange exchange) {
        return exchangeService.create(exchange);
    }
}
