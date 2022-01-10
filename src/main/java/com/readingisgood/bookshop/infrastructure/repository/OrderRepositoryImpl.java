package com.readingisgood.bookshop.infrastructure.repository;

import com.readingisgood.bookshop.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    MongoTemplate mongoTemplate;

    @Autowired
    public OrderRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save (Order order) {
        mongoTemplate.save(order, "order");
    }

    @Override
    public Optional<Order> getById (String orderId) {
        Order order = mongoTemplate.findById(orderId, Order.class, "order");
        return Optional.ofNullable(order);
    }

    @Override
    public HashSet<Order> getByIds (HashSet<String> orderIds) {
        HashSet<Order> orders = new HashSet<>();
        orderIds.forEach( orderId -> orders.add(mongoTemplate.findById(orderId, Order.class, "order")));
        return orders;
    }

    @Override
    public void update(String orderId, Integer status) {
        mongoTemplate.upsert(
                Query.query(Criteria.where("_id").is(orderId)),
                new Update().set("currentUsageStock", status), String.class, "book");
    }

    @Override
    public List<Order> getAllOrders () {
        List<Order> orders = mongoTemplate.findAll(Order.class);

        return orders;
    }
}
