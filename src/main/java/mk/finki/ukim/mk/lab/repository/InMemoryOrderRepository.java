package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryOrderRepository {

    public Optional<Order> save(Order o) {
        DataHolder.orderList.add(o);
        return Optional.of(o);
    }
    public List<Order> findAll() {
        return DataHolder.orderList;
    }
}
