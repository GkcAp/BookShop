package com.readingisgood.bookshop.application;

import com.readingisgood.bookshop.application.service.book.request.AddBookRequest;
import com.readingisgood.bookshop.application.service.customer.request.AddCustomerRequest;
import com.readingisgood.bookshop.application.service.order.request.AddOrderRequest;
import com.readingisgood.bookshop.domain.book.BookSetup;
import com.readingisgood.bookshop.domain.customer.CustomerSetup;
import com.readingisgood.bookshop.domain.order.OrderSetup;
import com.readingisgood.bookshop.domain.order.OrderStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

public class MockObjectBuilder {

    public static AddBookRequest addBookCommand() {
        AddBookRequest request = new AddBookRequest();
        request.setName("BookName");
        request.setPrice(15.9);
        request.setStockLimit(new Random().nextInt());
        return request;
    }

    public static BookSetup addBookSetup(AddBookRequest request){
        BookSetup bookSetup = new BookSetup();
        bookSetup.setId(UUID.randomUUID().toString());
        bookSetup.setName(request.getName());
        bookSetup.setPrice(request.getPrice());
        bookSetup.setStockLimit(request.getStockLimit());
        bookSetup.setCurrentUsageStock(0);
        return bookSetup;
    }

    public static AddCustomerRequest addCustomerCommand() {
        AddCustomerRequest request = new AddCustomerRequest();
        request.setName("name");
        request.setSurname("surname");
        return request;
    }

    public static CustomerSetup addCustomerSetup(AddCustomerRequest request){
        CustomerSetup customerSetup = new CustomerSetup();
        customerSetup.setId(UUID.randomUUID().toString());
        customerSetup.setName(request.getName());
        customerSetup.setSurname(request.getSurname());
        customerSetup.setOrders(new HashSet<>());
        return customerSetup;
    }

    public static AddOrderRequest addOrderCommand() {
        AddOrderRequest request = new AddOrderRequest();
        request.setCustomerId("123");
        request.setBooks(getBooks());
        request.setTotalPrice(100.0);
        return request;
    }

    @NotNull
    private static HashSet<String> getBooks() {
        HashSet<String> books = new HashSet<>();
        books.add("34");
        books.add("3");
        return books;
    }

    public static OrderSetup addOrderSetup(AddOrderRequest request){
        OrderSetup orderSetup = new OrderSetup();
        orderSetup.setId(UUID.randomUUID().toString());
        orderSetup.setCustomerId(request.getCustomerId());
        orderSetup.setBooks(request.getBooks());
        orderSetup.setTotalPrice(request.getTotalPrice());
        orderSetup.setStatus(OrderStatus.PENDING);
        orderSetup.setOrderCreatedDate(LocalDateTime.now());
        return orderSetup;
    }

    public static OrderSetup addOrderSetup(){
        OrderSetup orderSetup = new OrderSetup();
        orderSetup.setId(UUID.randomUUID().toString());
        orderSetup.setCustomerId("123");
        orderSetup.setBooks(getBooks());
        orderSetup.setOrderCreatedDate(LocalDateTime.now());
        orderSetup.setStatus(OrderStatus.PENDING);
        return orderSetup;
    }
}
