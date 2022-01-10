package com.readingisgood.bookshop.application.service.order.request;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

@Getter
@Setter
public class AddOrderRequest {

    @NotNull
    String customerId;

    @NotEmpty
    HashSet<String> books;

    @DecimalMin(value = "0.01", message = "TotalPriceMinError")
    Double totalPrice;
}
