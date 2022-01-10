package com.readingisgood.bookshop.domain.order;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;

@Getter
@Document
public class Order {
    @Id
    String id;
    String customerId;
    HashSet<String> books;
    Double totalPrice;
    LocalDateTime orderCreatedDate;
    Integer status;

    public Order(OrderSetup setUp) {
        this.id = setUp.getId();
        this.customerId = setUp.getCustomerId();
        this.books = setUp.getBooks();
        this.totalPrice = setUp.getTotalPrice();
        this.orderCreatedDate = setUp.getOrderCreatedDate();
        this.status = setUp.status.getValue();
    }


    public void changeStatus() {
         this.status = OrderStatus.COMPLETED.getValue();
    }
}
