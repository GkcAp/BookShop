package com.readingisgood.bookshop.domain.customer;

import com.readingisgood.bookshop.domain.book.BookSetup;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Getter
@Document
public class Customer {
    @Id
    String id;
    String name;
    String surname;
    HashSet<String> orders;

    public Customer(CustomerSetup setUp) {
        this.id = setUp.getId();
        this.name = setUp.getName();
        this.surname = setUp.getSurname();
        this.orders = setUp.getOrders();
    }
}
