package be.vdab.luigi.pizzas;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class PizzaRepository {

    private final JdbcClient jdbcClient;

    PizzaRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    long findAantal() {
        String sql = """
                select count(*) as aantalPizzas
                from pizzas
                """;
        return jdbcClient.sql(sql)
                .query(Long.class)
                .single();
    }

    Optional<Pizza> findById(long id) {
        String sql = """
                select id, naam, prijs, winst
                from pizzas
                where id = ?
                """;
        return jdbcClient.sql(sql)
                .param(id)
                .query(Pizza.class)
                .optional();
    }
}
