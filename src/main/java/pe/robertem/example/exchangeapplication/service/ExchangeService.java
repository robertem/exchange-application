package pe.robertem.example.exchangeapplication.service;

import org.springframework.stereotype.Service;
import pe.robertem.example.exchangeapplication.entity.Currency;
import pe.robertem.example.exchangeapplication.entity.Exchange;
import pe.robertem.example.exchangeapplication.exception.InvalidCurrencyException;
import pe.robertem.example.exchangeapplication.repository.ExchangeRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Service
public class ExchangeService {

    private static final Integer MAX_AMOUNT_SCALE = 2;

    private final ExchangeRateService exchangeRateService;
    private final CurrencyService currencyService;
    private final ExchangeRepository exchangeRepository;

    public ExchangeService(ExchangeRateService exchangeRateService,
                           CurrencyService currencyService,
                           ExchangeRepository exchangeRepository) {
        this.exchangeRateService = exchangeRateService;
        this.currencyService = currencyService;
        this.exchangeRepository = exchangeRepository;
    }

    public Exchange create(Exchange exchange) {
        Currency localCurrency = currencyService.getLocalCurrency();

        validateExchangeCurrenciesFromLocal(exchange.getSourceCurrency(), exchange.getDestinationCurrency(),
                localCurrency);

        BigDecimal exchangeRate;
        BigDecimal destinationAmount;

        if (isExchangeBid(exchange.getDestinationCurrency(), localCurrency)) {
            exchangeRate = exchangeRateService.findLatestByCurrency(exchange.getSourceCurrency().getId()).getBid();
            destinationAmount = exchange.getSourceAmount().multiply(exchangeRate).setScale(MAX_AMOUNT_SCALE,
                    RoundingMode.HALF_UP);
        } else {
            exchangeRate = exchangeRateService.findLatestByCurrency(exchange.getDestinationCurrency().getId()).getAsk();
            destinationAmount = exchange.getSourceAmount().divide(exchangeRate, MAX_AMOUNT_SCALE, RoundingMode.HALF_UP);
        }

        exchange.setRate(exchangeRate);
        exchange.setDestinationAmount(destinationAmount);
        exchange.setCreationDate(LocalDateTime.now());

        return exchangeRepository.save(exchange);
    }

    public boolean isExchangeBid(final Currency destinationCurrency,
                                 final Currency localCurrency) {
        return localCurrency.getId().equals(destinationCurrency.getId());
    }

    public void validateExchangeCurrenciesFromLocal(final Currency sourceCurrency, final Currency destinationCurrency,
                                                    final Currency localCurrency) {
        if (sourceCurrency.getId().equals(destinationCurrency.getId())) {
            throw new InvalidCurrencyException("Source and target currency can't be the same");
        }

        if (!localCurrency.getId().equals(sourceCurrency.getId()) && !localCurrency.getId().equals(
                destinationCurrency.getId())) {
            throw new InvalidCurrencyException(
                    "Source or target currency must be the local currency " + localCurrency.getId());
        }
    }

}
