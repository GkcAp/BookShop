package com.readingisgood.bookshop.application.service.order.response;

import com.readingisgood.bookshop.domain.customer.Customer;
import com.readingisgood.bookshop.domain.order.Order;

import java.time.LocalDateTime;
import java.util.HashSet;


public class GetOrderResponse {

    String id;
    HashSet<String> books;
    Double totalPrice;
    LocalDateTime orderCreatedDate;

    public static GetOrderResponse buildFrom(Order order) {
        final GetOrderResponse orderResponse = new GetOrderResponse();
        orderResponse.id = order.getId();
        orderResponse.books = order.getBooks();
        orderResponse.totalPrice = order.getTotalPrice();
        orderResponse.orderCreatedDate = order.getOrderCreatedDate();
        return orderResponse;
    }
}
