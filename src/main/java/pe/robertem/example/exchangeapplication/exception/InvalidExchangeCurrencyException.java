package pe.robertem.example.exchangeapplication.exception;

public class InvalidExchangeCurrencyException extends RuntimeException {

    public InvalidExchangeCurrencyException(String message) {
        super(message);
    }
}
