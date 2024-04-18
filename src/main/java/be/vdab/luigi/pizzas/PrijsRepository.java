package be.vdab.luigi.pizzas;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class PrijsRepository {
    private final JdbcClient jdbcClient;

    PrijsRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    void create(Prijs prijs) {
        String sql = """
                insert into prijzen(prijs, vanaf, pizzaId)
                values (?, ?, ?);
                """;
        jdbcClient.sql(sql)
                .params(prijs.getPrijs(), prijs.getVanaf(), prijs.getPizzaId())
                .update();
    }
    List<Prijs> findByPizzaId(long pizzaId) {
        String sql = """
                select prijs, vanaf, pizzaId
                from prijzen
                where pizzaId = ?
                order by vanaf
                """;
        return jdbcClient.sql(sql)
                .param(pizzaId)
                .query(Prijs.class)
                .list();
    }
}
