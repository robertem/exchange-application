package pe.robertem.example.exchangeapplication.exception;

public class InvalidCurrencyException extends RuntimeException {

    public InvalidCurrencyException(String message) {
        super(message);
    }
}
