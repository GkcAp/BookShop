package com.readingisgood.bookshop.application.service.order.request;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class GetOrdersBetweenDatesRequest {

    LocalDateTime startDate;
    LocalDateTime endDate;

}
