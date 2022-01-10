package com.readingisgood.bookshop.application.service.customer;

import com.readingisgood.bookshop.application.service.customer.request.AddCustomerRequest;
import com.readingisgood.bookshop.domain.customer.CustomerSetup;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;

@Component
public class CustomerAssembler {

    public CustomerSetup createBookRequestToCustomerSetup (AddCustomerRequest request) {
        CustomerSetup customerSetup = new CustomerSetup();
        customerSetup.setId(UUID.randomUUID().toString());
        customerSetup.setName(request.getName());
        customerSetup.setSurname(request.getSurname());
        customerSetup.setOrders(new HashSet<>());
        return customerSetup;
    }
}
