package com.readingisgood.bookshop.application.controller;

import com.readingisgood.bookshop.application.service.order.OrderService;
import com.readingisgood.bookshop.application.service.order.request.AddOrderRequest;
import com.readingisgood.bookshop.application.service.order.request.GetOrdersBetweenDatesRequest;
import com.readingisgood.bookshop.application.service.order.response.GetOrderResponse;
import com.readingisgood.bookshop.application.service.order.response.GetOrdersResponse;
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
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody AddOrderRequest addOrderRequest) {
        String orderId = orderService.add(addOrderRequest);
        return ResponseEntity.created(URI.create("/order/" + orderId)).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<GetOrderResponse> getById(@PathVariable String orderId) {
        GetOrderResponse getOrderResponse = orderService.getById(orderId);
        return new ResponseEntity<>(getOrderResponse,OK);
    }

    @GetMapping("/getByBetweenDates")
    public ResponseEntity<GetOrdersResponse> getByBetweenDates(@Valid @RequestBody GetOrdersBetweenDatesRequest getOrdersBetweenDatesRequest) {
        GetOrdersResponse getOrderResponse = orderService.getOrdersBetweenDates(getOrdersBetweenDatesRequest);
        return new ResponseEntity<>(getOrderResponse,OK);
    }
}
