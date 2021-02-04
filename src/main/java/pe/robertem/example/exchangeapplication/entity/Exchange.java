package pe.robertem.example.exchangeapplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Exchange {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private Currency sourceCurrency;
    private BigDecimal sourceAmount;
    @ManyToOne
    private Currency destinationCurrency;
    private BigDecimal destinationAmount;
    private BigDecimal rate;
    private LocalDateTime creationDate;

}
