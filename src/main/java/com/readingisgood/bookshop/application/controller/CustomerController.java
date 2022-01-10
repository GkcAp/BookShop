package com.readingisgood.bookshop.application.controller;

import com.readingisgood.bookshop.application.service.customer.CustomerService;
import com.readingisgood.bookshop.application.service.customer.request.AddCustomerRequest;
import com.readingisgood.bookshop.application.service.customer.response.GetCustomerResponse;
import com.readingisgood.bookshop.application.service.customer.response.GetCustomersOrdersResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<GetCustomerResponse> getById(@PathVariable String customerId) {
        GetCustomerResponse getCustomerResponse = customerService.getById(customerId);
        return new ResponseEntity<>(getCustomerResponse,OK);
    }

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody AddCustomerRequest addCouponRequest) {
        String couponId = customerService.add(addCouponRequest);
        return ResponseEntity.created(URI.create("/customer/" + couponId)).build();
    }

    @GetMapping("/orders/{customerId}")
    public ResponseEntity<GetCustomersOrdersResponse> getOrders(@PathVariable String customerId) {
        GetCustomersOrdersResponse getCustomersOrdersResponse = customerService.getCustomersOrders(customerId);
        return new ResponseEntity<>(getCustomersOrdersResponse,OK);
    }

}
