package com.readingisgood.bookshop.application.service.book.request;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class AddBookRequest {
    //According to the need, the id can be taken in the request, if it is received, it should be checked whether a data with that id has been added before.
    private String id;

    @NotEmpty(message = "NameNullError")
    String name;

    @DecimalMin(value = "0.01", message = "PriceMinError")
    double price;

    @Min(value = 0, message = "StockLimitMinError")
    int stockLimit;

}
