package com.readingisgood.bookshop.application.controller;

import com.readingisgood.bookshop.application.service.statistics.StatisticsService;
import com.readingisgood.bookshop.application.service.statistics.request.GetStatisticsCustomersMonthlyRequest;
import com.readingisgood.bookshop.application.service.statistics.response.GetStatisticsCustomersMonthlyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

public class StatisticsController {

    StatisticsService statisticsService;

    @GetMapping("/orders/{customerId}")
    public ResponseEntity<GetStatisticsCustomersMonthlyResponse> getOrders(@Valid @RequestBody GetStatisticsCustomersMonthlyRequest getStatisticsCustomersMonthlyRequest) {
        GetStatisticsCustomersMonthlyResponse getStatisticsCustomersMonthlyResponse = statisticsService.getById(getStatisticsCustomersMonthlyRequest);
        return new ResponseEntity<>(getStatisticsCustomersMonthlyResponse,OK);
    }
}
