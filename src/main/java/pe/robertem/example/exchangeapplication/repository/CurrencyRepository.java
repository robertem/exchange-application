package pe.robertem.example.exchangeapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.robertem.example.exchangeapplication.entity.Currency;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

    Optional<Currency> findByLocal(Boolean local);

}
