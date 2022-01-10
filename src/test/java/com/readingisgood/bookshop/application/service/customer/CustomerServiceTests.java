package com.readingisgood.bookshop.application.service.customer;

import com.readingisgood.bookshop.application.MockObjectBuilder;
import com.readingisgood.bookshop.application.exception.NotFoundException;
import com.readingisgood.bookshop.application.service.customer.request.AddCustomerRequest;
import com.readingisgood.bookshop.application.service.customer.response.GetCustomerResponse;
import com.readingisgood.bookshop.domain.customer.Customer;
import com.readingisgood.bookshop.domain.customer.CustomerSetup;
import com.readingisgood.bookshop.infrastructure.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.readingisgood.bookshop.application.MockObjectBuilder.addCustomerCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerAssembler customerAssembler;

    @Test
    void add_WhenCalled_ShouldReturnCustomer() {
        //Arrange
        AddCustomerRequest request = addCustomerCommand();
        CustomerSetup customerSetup = MockObjectBuilder.addCustomerSetup(request);
        given(customerAssembler.createBookRequestToCustomerSetup(request)).willReturn(customerSetup);

        //Act
        String result = customerService.add(request);

        //Assert
        assertEquals(result, customerSetup.getId());
    }

    @Test
    void getById_WhenCalled_ShouldReturnCustomer() {
        //Arrange
        AddCustomerRequest request = addCustomerCommand();
        CustomerSetup customerSetup = MockObjectBuilder.addCustomerSetup(request);
        Customer customer = new Customer(customerSetup);
        given(customerRepository.getById(customerSetup.getId())).willReturn(Optional.of(customer));

        //Act
        GetCustomerResponse getCustomerResponse = customerService.getById(customer.getId());

        //Assert
        assertThat(getCustomerResponse).usingRecursiveComparison()
                .isEqualTo(customer);
    }

    @Test
    void getById_WhenCustomerIdIsNotExist_ShouldReturnCustomerIdNotFoundError() {
        //Arrange
        AddCustomerRequest request = addCustomerCommand();
        CustomerSetup customerSetup = MockObjectBuilder.addCustomerSetup(request);
        given(customerRepository.getById(customerSetup.getId())).willReturn(Optional.ofNullable(null));

        //Act
        Throwable exception = catchThrowable(() -> customerService.getById(customerSetup.getId()));

        //Assert
        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertEquals("CustomerIdNotFoundError", ((NotFoundException) exception).getKey());
    }
}
