package pe.robertem.example.exchangeapplication.service;

import org.springframework.stereotype.Service;
import pe.robertem.example.exchangeapplication.entity.Currency;
import pe.robertem.example.exchangeapplication.exception.LocalCurrencyNotFoundException;
import pe.robertem.example.exchangeapplication.repository.CurrencyRepository;

@Service
public class CurrencyService {

    private static final Boolean IS_LOCAL = true;

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Currency getLocalCurrency() {
        return currencyRepository.findByLocal(IS_LOCAL).orElseThrow(() -> new LocalCurrencyNotFoundException());
    }

}
