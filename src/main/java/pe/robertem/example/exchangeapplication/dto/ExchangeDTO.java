package pe.robertem.example.exchangeapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeDTO {

    private ExchangeElement source;
    private ExchangeElement destination;

    private class ExchangeElement {

        private String currency;
        private BigDecimal amount;

    }

}
