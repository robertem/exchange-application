package pe.robertem.example.exchangeapplication.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.robertem.example.exchangeapplication.entity.Currency;
import pe.robertem.example.exchangeapplication.entity.ExchangeRate;
import pe.robertem.example.exchangeapplication.exception.InvalidExchangeCurrencyException;
import pe.robertem.example.exchangeapplication.repository.ExchangeRateRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExchangeRateService {

    public static final Boolean CURRENCY_IS_ACTIVE = true;
    public static final Boolean CURRENCY_IS_NOT_LOCAL = false;
    public static final String CREATION_DATE_SORT_PROPERTY = "creationDate";

    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyService currencyService;

    public ExchangeRateService(
            ExchangeRateRepository exchangeRateRepository,
            CurrencyService currencyService) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.currencyService = currencyService;
    }

    public List<ExchangeRate> findByCurrency(String currencyId) {

        ExchangeRate exchangeRate = new ExchangeRate();
        Currency currency = new Currency();
        currency.setActive(CURRENCY_IS_ACTIVE);
        currency.setLocal(CURRENCY_IS_NOT_LOCAL);
        currency.setId(currencyId);
        exchangeRate.setCurrency(currency);

        return exchangeRateRepository.findAll(Example.of(exchangeRate),
                Sort.by(CREATION_DATE_SORT_PROPERTY).descending());
    }

    public ExchangeRate findLatestByCurrency(String currencyId) {

        List<ExchangeRate> exchangeRates = this.findByCurrency(currencyId);

        if (exchangeRates.isEmpty()) {
            throw new InvalidExchangeCurrencyException("There is not exchange rate for the currency " + currencyId);
        } else {
            return exchangeRates.get(0);
        }
    }

    public ExchangeRate create(ExchangeRate exchangeRate) {

        Currency localCurrency = currencyService.getLocalCurrency();

        if (localCurrency.getId().equals(exchangeRate.getCurrency().getId())) {
            throw new InvalidExchangeCurrencyException("Currency of exchange rate must be different from local currency");
        }

        exchangeRate.setCreationDate(LocalDateTime.now());

        return exchangeRateRepository.save(exchangeRate);
    }
}
