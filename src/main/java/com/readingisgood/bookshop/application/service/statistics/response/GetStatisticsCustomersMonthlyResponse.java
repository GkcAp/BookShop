package com.readingisgood.bookshop.application.service.statistics.response;

import com.readingisgood.bookshop.domain.order.OrderSetup;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GetStatisticsCustomersMonthlyResponse {

    Map<String, List<OrderSetup>> orders;

}
