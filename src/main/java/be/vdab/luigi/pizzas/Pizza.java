package be.vdab.luigi.pizzas;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter()
class Pizza {
    private final long id;
    private final String naam;
    private final BigDecimal prijs;
    private final BigDecimal winst;
}
