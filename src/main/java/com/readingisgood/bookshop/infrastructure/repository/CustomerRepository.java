package com.readingisgood.bookshop.infrastructure.repository;

import com.readingisgood.bookshop.domain.customer.Customer;

import java.util.Optional;

public interface CustomerRepository {

    void save(Customer customer);

    Optional<Customer> getById (String customerId);
}
