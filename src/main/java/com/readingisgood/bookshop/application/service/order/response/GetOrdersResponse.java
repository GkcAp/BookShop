package com.readingisgood.bookshop.application.service.order.response;

import com.readingisgood.bookshop.domain.order.Order;
import com.readingisgood.bookshop.domain.order.OrderSetup;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
public class GetOrdersResponse {

    HashSet<OrderSetup> orders;

    public static GetOrdersResponse buildFrom(HashSet<Order> orders) {
        final GetOrdersResponse ordersResponse = new GetOrdersResponse();
        HashSet<OrderSetup> orderSetups = new HashSet<>();
        orders.forEach(order -> {
            OrderSetup orderSetup = new OrderSetup();
            orderSetup.setId(order.getId());
            orderSetup.setCustomerId(order.getCustomerId());
            orderSetup.setBooks(order.getBooks());
            orderSetup.setTotalPrice(order.getTotalPrice());
            orderSetup.setOrderCreatedDate(order.getOrderCreatedDate());
            orderSetups.add(orderSetup);
        });
        ordersResponse.setOrders(orderSetups);
        return ordersResponse;
    }
}
