package com.readingisgood.bookshop.application.service.statistics.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GetStatisticsCustomersMonthlyRequest {

    String customerId;
    Integer year;
    Integer month;
}
