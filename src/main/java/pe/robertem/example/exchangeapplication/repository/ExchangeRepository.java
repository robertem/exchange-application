package pe.robertem.example.exchangeapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.robertem.example.exchangeapplication.entity.Exchange;

public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {
}
