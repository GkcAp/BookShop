package com.readingisgood.bookshop.application.service.customer.response;

import com.readingisgood.bookshop.domain.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
public class GetCustomerResponse {

    String id;
    String name;
    String surname;
    HashSet<String> orders;

    public static GetCustomerResponse buildFrom(Customer customer) {
        final GetCustomerResponse getCustomerResponse = new GetCustomerResponse();
        getCustomerResponse.id = customer.getId();
        getCustomerResponse.name = customer.getName();
        getCustomerResponse.surname = customer.getSurname();
        getCustomerResponse.orders = customer.getOrders();
        return getCustomerResponse;
    }
}
