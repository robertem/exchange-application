package pe.robertem.example.exchangeapplication.exception;

public class LocalCurrencyNotFoundException extends RuntimeException {

    public LocalCurrencyNotFoundException() {
        super("Local currency not defined");
    }
}
