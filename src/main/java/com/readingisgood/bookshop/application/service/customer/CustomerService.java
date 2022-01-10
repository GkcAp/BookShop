package com.readingisgood.bookshop.application.service.customer;

import com.readingisgood.bookshop.application.exception.NotFoundException;
import com.readingisgood.bookshop.application.service.customer.request.AddCustomerRequest;
import com.readingisgood.bookshop.application.service.customer.response.GetCustomerResponse;
import com.readingisgood.bookshop.application.service.customer.response.GetCustomersOrdersResponse;
import com.readingisgood.bookshop.application.service.order.OrderService;
import com.readingisgood.bookshop.application.service.order.response.GetOrdersResponse;
import com.readingisgood.bookshop.domain.customer.Customer;
import com.readingisgood.bookshop.domain.customer.CustomerSetup;
import com.readingisgood.bookshop.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerAssembler customerAssembler;
    private final OrderService orderService;

    public GetCustomerResponse getById(String customerId) {
        Customer customer = customerRepository.getById(customerId).orElseThrow(() -> new NotFoundException("CustomerIdNotFoundError"));
        return GetCustomerResponse.buildFrom(customer);
    }

    public String add(AddCustomerRequest createCustomerRequest ) {
        CustomerSetup customerSetup = customerAssembler.createBookRequestToCustomerSetup(createCustomerRequest);
        Customer customer = new Customer(customerSetup);
        customerRepository.save(customer);
        return customer.getId();
    }

    public GetCustomersOrdersResponse getCustomersOrders(String customerId) {
        HashSet<String> customerOrderIds = getById(customerId).getOrders();
        GetOrdersResponse getOrdersResponse = orderService.getByIds(customerOrderIds);
        return (GetCustomersOrdersResponse) getOrdersResponse;
    }
}
