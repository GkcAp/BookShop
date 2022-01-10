package com.readingisgood.bookshop.infrastructure.repository;

import com.readingisgood.bookshop.domain.order.Order;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    void save(Order order);

    Optional<Order> getById (String orderId);

    HashSet<Order> getByIds (HashSet<String> orderIds);

    void update(String orderId, Integer status);

    List<Order> getAllOrders () ;

}
