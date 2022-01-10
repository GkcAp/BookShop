package com.readingisgood.bookshop.application.service.order;

import com.readingisgood.bookshop.application.service.order.request.AddOrderRequest;
import com.readingisgood.bookshop.domain.order.OrderSetup;
import com.readingisgood.bookshop.domain.order.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class OrderAssembler {

    public OrderSetup addOrderRequestToOrderSetup (AddOrderRequest request) {
        OrderSetup orderSetup = new OrderSetup();
        orderSetup.setId(UUID.randomUUID().toString());
        orderSetup.setCustomerId(request.getCustomerId());
        orderSetup.setBooks(request.getBooks());
        orderSetup.setTotalPrice(request.getTotalPrice());
        orderSetup.setOrderCreatedDate(LocalDateTime.now());
        orderSetup.setStatus(OrderStatus.PENDING);
        return orderSetup;
    }
}
