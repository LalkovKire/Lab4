package mk.finki.ukim.mk.lab.repository.jpaRepository;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findOrdersByDatumIsBetween(LocalDateTime datum1,LocalDateTime datum2);

}
