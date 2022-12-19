package mk.finki.ukim.mk.lab.service.Implementation;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.InMemoryOrderRepository;
import mk.finki.ukim.mk.lab.repository.jpaRepository.OrderRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {

    private  final OrderRepository orderRepository;

    public OrderServiceImplementation(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String balloonColor, String balloonSize, LocalDateTime dateTime) {
        Order order = new Order(balloonColor,balloonSize,dateTime);
        return order;
    }

    @Override
    public Order insert(Order o) {
        return orderRepository.save(o);
    }

    @Override
    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> filterOrders(LocalDateTime datum1, LocalDateTime datum2) {
         return this.orderRepository.findOrdersByDatumIsBetween(datum1,datum2);
    }

    @Override
    public List<Order> sortingOrders(List<Order> list) {
        Comparator<Order> comparatorAsc = Comparator.comparing(Order::getDatum);

        Collections.sort(list, comparatorAsc);

        return list;
    }

}
