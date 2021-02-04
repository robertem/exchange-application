package pe.robertem.example.exchangeapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.robertem.example.exchangeapplication.exception.InvalidExchangeCurrencyException;
import pe.robertem.example.exchangeapplication.exception.LocalCurrencyNotFoundException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value
            = {InvalidExchangeCurrencyException.class, LocalCurrencyNotFoundException.class})
    protected ResponseEntity<Object> handleCustomExceptions(RuntimeException exception) {
        return new ResponseEntity<>(buildResponseBody(HttpStatus.BAD_REQUEST, exception), HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> buildResponseBody(HttpStatus status, Exception ex) {

        Map<String, Object> body = new LinkedHashMap<>();

        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("exception", ex.getClass().getName());

        return body;
    }

}
