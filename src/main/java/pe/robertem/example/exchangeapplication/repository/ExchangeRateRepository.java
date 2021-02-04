package pe.robertem.example.exchangeapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.robertem.example.exchangeapplication.entity.ExchangeRate;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {

}
