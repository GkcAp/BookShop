package com.readingisgood.bookshop.application.controller;

import com.readingisgood.bookshop.application.service.statistics.StatisticsService;
import com.readingisgood.bookshop.application.service.statistics.request.GetStatisticsCustomersMonthlyRequest;
import com.readingisgood.bookshop.application.service.statistics.response.GetStatisticsCustomersMonthlyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    StatisticsService statisticsService;

    @GetMapping("/orders/{customerId}")
    public ResponseEntity<GetStatisticsCustomersMonthlyResponse> getOrders(@Valid @RequestBody GetStatisticsCustomersMonthlyRequest getStatisticsCustomersMonthlyRequest) {
        GetStatisticsCustomersMonthlyResponse getStatisticsCustomersMonthlyResponse = statisticsService.getById(getStatisticsCustomersMonthlyRequest);
        return new ResponseEntity<>(getStatisticsCustomersMonthlyResponse,OK);
    }
}
