package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order placeOrder(String balloonColor, String balloonSize, LocalDateTime dateTime);

    Order insert(Order o);

    List<Order> listAll();

    List<Order> filterOrders(LocalDateTime datum1, LocalDateTime datum2);

    List<Order> sortingOrders(List<Order> list);
}
