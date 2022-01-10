package com.readingisgood.bookshop.infrastructure.repository;

import com.readingisgood.bookshop.domain.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepositoryImpl implements  CustomerRepository {

    MongoTemplate mongoTemplate;

    @Autowired
    public CustomerRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save (Customer customer) {
        mongoTemplate.save(customer, "customer");
    }

    public Optional<Customer> getById (String customerId) {
        return Optional.ofNullable(mongoTemplate.findById(customerId, Customer.class, "customer"));
    }
}
