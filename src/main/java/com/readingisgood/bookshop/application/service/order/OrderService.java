package com.readingisgood.bookshop.application.service.order;

import com.ibm.icu.text.BidiTransform;
import com.readingisgood.bookshop.application.exception.NotFoundException;
import com.readingisgood.bookshop.application.service.book.BookService;
import com.readingisgood.bookshop.application.service.order.request.AddOrderRequest;
import com.readingisgood.bookshop.application.service.order.request.GetOrdersBetweenDatesRequest;
import com.readingisgood.bookshop.application.service.order.response.GetOrderResponse;
import com.readingisgood.bookshop.application.service.order.response.GetOrdersResponse;
import com.readingisgood.bookshop.domain.order.Order;
import com.readingisgood.bookshop.domain.order.OrderSetup;
import com.readingisgood.bookshop.domain.order.OrderStatus;
import com.readingisgood.bookshop.infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderAssembler orderAssembler;
    private final OrderValidation orderValidation;
    private final BookService bookService;

    public GetOrderResponse getById(String orderId) {
        Order order = orderRepository.getById(orderId).orElseThrow(() -> new NotFoundException("OrderIdNotFoundError"));
        return GetOrderResponse.buildFrom(order);
    }

    public String add(AddOrderRequest addOrderRequest ) {
        orderValidation.validateAddOrder(addOrderRequest);
        OrderSetup orderSetup = orderAssembler.addOrderRequestToOrderSetup(addOrderRequest);
        Order order = new Order(orderSetup);
        orderRepository.save(order);
        order.getBooks().forEach(bookService::update);
        order.changeStatus();
        orderRepository.update(order.getId(), OrderStatus.COMPLETED.getValue());
        return order.getId();
    }

    public GetOrdersResponse getByIds(HashSet<String> orderIds) {
        HashSet<Order> orders =  orderRepository.getByIds(orderIds);
        return GetOrdersResponse.buildFrom(orders);
    }

    public GetOrdersResponse getOrdersBetweenDates(GetOrdersBetweenDatesRequest getOrdersBetweenDatesRequest) {
        List<Order> orders =  orderRepository.getAllOrders();
        LocalDateTime startDate = getOrdersBetweenDatesRequest.getStartDate();
        LocalDateTime endDate = getOrdersBetweenDatesRequest.getEndDate();
        HashSet<Order> filteredOrders = new HashSet<>();

        orders.stream().forEach(order -> {
            if (order.getOrderCreatedDate().isAfter(startDate) && order.getOrderCreatedDate().isBefore(endDate))
                filteredOrders.add(order);
        });
        return GetOrdersResponse.buildFrom(filteredOrders);
    }
}
