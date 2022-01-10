package com.readingisgood.bookshop.domain.order;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;

@Getter
@Setter
public class OrderSetup {
    String id;
    String customerId;
    HashSet<String> books;
    Double totalPrice;
    LocalDateTime orderCreatedDate;
    OrderStatus status;

    public String getMonthOrders() {
        return MonthOrder.valueOf(orderCreatedDate.getMonthValue()).name();
    }
}
