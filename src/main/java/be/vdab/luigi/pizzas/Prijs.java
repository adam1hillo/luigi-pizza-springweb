package be.vdab.luigi.pizzas;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
class Prijs {
    private final BigDecimal prijs;
    private final LocalDateTime vanaf;
    private final long pizzaId;
}
