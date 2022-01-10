package com.readingisgood.bookshop.application.service.customer.request;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddCustomerRequest {

    @NotNull
    String name;

    @NotNull
    String surname;
}
