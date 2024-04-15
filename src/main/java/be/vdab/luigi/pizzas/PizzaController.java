package be.vdab.luigi.pizzas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.stream.Stream;

@RestController
class PizzaController {
    private final PizzaService pizzaService;

    PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("pizzas/aantal")
    long findAantal() {
        return pizzaService.findAantal();
    }
    @GetMapping("pizzas/{id}")
    IdNaamPrijs findById(@PathVariable long id) {
        return pizzaService.findById(id)
                .map(IdNaamPrijs::new)
                .orElseThrow(() -> new PizzaNietGevondenException(id));
    }

    @GetMapping("pizzas")
    Stream<IdNaamPrijs> findAll() {
        return pizzaService.findAll()
                .stream()
                .map(IdNaamPrijs::new);
    }
    @GetMapping(value = "pizzas", params = "naamBevat")
    Stream<IdNaamPrijs> findByNaamBevat(String naamBevat) {
        return pizzaService.findByNaamBevat(naamBevat)
                .stream()
                .map(IdNaamPrijs::new);
    }
    @GetMapping(value = "pizzas", params = {"vanPrijs", "totPrijs"})
    Stream<IdNaamPrijs> findByPrijsTussen(BigDecimal vanPrijs, BigDecimal totPrijs) {
        return pizzaService.findByPrijsTussen(vanPrijs, totPrijs)
                .stream()
                .map(IdNaamPrijs::new);
    }
    private record IdNaamPrijs(long id, String naam, BigDecimal prijs) {
        IdNaamPrijs(Pizza pizza) {
            this(pizza.getId(), pizza.getNaam(), pizza.getPrijs());
        }
    }
}
