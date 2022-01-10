package com.readingisgood.bookshop.application.service.statistics;

import com.readingisgood.bookshop.application.service.customer.CustomerService;
import com.readingisgood.bookshop.application.service.order.OrderService;
import com.readingisgood.bookshop.application.service.statistics.request.GetStatisticsCustomersMonthlyRequest;
import com.readingisgood.bookshop.application.service.statistics.response.GetStatisticsCustomersMonthlyResponse;
import com.readingisgood.bookshop.domain.order.OrderSetup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final CustomerService customerService;

    public GetStatisticsCustomersMonthlyResponse getById(GetStatisticsCustomersMonthlyRequest getStatisticsCustomersMonthlyRequest) {

        String customerId = getStatisticsCustomersMonthlyRequest.getCustomerId();
        Integer year = getStatisticsCustomersMonthlyRequest.getYear();
        Integer month = getStatisticsCustomersMonthlyRequest.getMonth();

        List<OrderSetup> orders = customerService.getCustomersOrders(customerId).getOrders().stream().toList();
        Map<String, List<OrderSetup>> order = orders.stream().filter(e -> e.getOrderCreatedDate().getMonthValue() == month)
                .filter(e -> e.getOrderCreatedDate().getYear() == year)
                .collect(Collectors.groupingBy(OrderSetup::getMonthOrders));

        return (GetStatisticsCustomersMonthlyResponse) order;
    }

}
