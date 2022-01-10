package com.readingisgood.bookshop.application.service.customer;

import com.readingisgood.bookshop.application.service.customer.request.AddCustomerRequest;
import com.readingisgood.bookshop.domain.customer.CustomerSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static com.readingisgood.bookshop.application.MockObjectBuilder.addCustomerCommand;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CustomerAssemblerTests {

    CustomerAssembler customerAssembler;

    @BeforeEach
    public void init() {
        customerAssembler = new CustomerAssembler();
    }

    @Test
    public void addCustomerRequestToCustomerSetup() {

        //Arrange
        AddCustomerRequest request = addCustomerCommand();

        //Act
        CustomerSetup result = customerAssembler.createBookRequestToCustomerSetup(request);

        //Assert
        assertThat(result).usingRecursiveComparison()
                .ignoringFields("id","orders")
                .isEqualTo(request);
    }

}