package com.readingisgood.bookshop.application.service.order;

import com.readingisgood.bookshop.application.service.customer.request.AddCustomerRequest;
import com.readingisgood.bookshop.application.service.order.request.AddOrderRequest;
import com.readingisgood.bookshop.domain.customer.CustomerSetup;
import com.readingisgood.bookshop.domain.order.OrderSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;

import static com.readingisgood.bookshop.application.MockObjectBuilder.addOrderCommand;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OrderAssemblerTest {
    OrderAssembler orderAssembler;


    @BeforeEach
    public void init() {
        orderAssembler = new OrderAssembler();
    }

    @Test
    public void addOrderRequestToOrderSetup() {

        //Arrange
        AddOrderRequest request = addOrderCommand();

        //Act
        OrderSetup result = orderAssembler.addOrderRequestToOrderSetup(request);

        //Assert
        assertThat(result).usingRecursiveComparison()
                .ignoringFields("id","orderCreatedDate", "status")
                .isEqualTo(request);
    }

}