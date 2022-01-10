package com.readingisgood.bookshop.application.service.order;

import com.readingisgood.bookshop.application.exception.NotFoundException;
import com.readingisgood.bookshop.application.service.book.BookService;
import com.readingisgood.bookshop.application.service.customer.request.AddCustomerRequest;
import com.readingisgood.bookshop.application.service.customer.response.GetCustomerResponse;
import com.readingisgood.bookshop.application.service.order.request.AddOrderRequest;
import com.readingisgood.bookshop.application.service.order.response.GetOrderResponse;
import com.readingisgood.bookshop.application.service.order.response.GetOrdersResponse;
import com.readingisgood.bookshop.domain.customer.Customer;
import com.readingisgood.bookshop.domain.customer.CustomerSetup;
import com.readingisgood.bookshop.domain.order.Order;
import com.readingisgood.bookshop.domain.order.OrderSetup;
import com.readingisgood.bookshop.domain.order.OrderStatus;
import com.readingisgood.bookshop.infrastructure.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;

import static com.readingisgood.bookshop.application.MockObjectBuilder.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderAssembler orderAssembler;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderValidation orderValidation;

    @Mock
    private  BookService bookService;


    @Test
    void add_WhenCalled_ShouldReturnOrder() {
        //Arrange
        AddOrderRequest request = addOrderCommand();
        OrderSetup orderSetup = addOrderSetup(request);

        doAnswer(i -> null).when(orderValidation).validateAddOrder(request);
        given(orderAssembler.addOrderRequestToOrderSetup(request)).willReturn(orderSetup);
        doAnswer(i -> null).when(bookService).update("34");
        doAnswer(i -> null).when(orderRepository).update(orderSetup.getId(), OrderStatus.COMPLETED.getValue());

        //Act
        String result = orderService.add(request);

        //Assert
        assertEquals(result, orderSetup.getId());
    }

    @Test
    void getById_WhenCalled_ShouldReturnOrder() {
        //Arrange
        AddOrderRequest request = addOrderCommand();
        OrderSetup orderSetup = addOrderSetup(request);
        Order order = new Order(orderSetup);
        given(orderRepository.getById(orderSetup.getId())).willReturn(Optional.of(order));

        //Act
        GetOrderResponse getOrderResponse = orderService.getById(order.getId());

        //Assert
        assertThat(getOrderResponse).usingRecursiveComparison()
                .isEqualTo(order);
    }

    @Test
    void getById_WhenOrderIdIsNotExist_ShouldReturnOrderIdNotFoundError() {
        //Arrange
        AddOrderRequest request = addOrderCommand();
        OrderSetup orderSetup = addOrderSetup(request);
        Order order = new Order(orderSetup);
        given(orderRepository.getById(orderSetup.getId())).willReturn(Optional.ofNullable(null));

        //Act
        Throwable exception = catchThrowable(() -> orderService.getById(orderSetup.getId()));

        //Assert
        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertEquals("OrderIdNotFoundError", ((NotFoundException) exception).getKey());
    }

    @Test
    void getByIds_WhenCalled_ShouldReturnOrders() {
        //Arrange
        OrderSetup setUp = addOrderSetup();
        Order order = new Order(setUp);
        HashSet<Order> orders = new HashSet<>();
        orders.add(order);
        HashSet<String> orderIds = new HashSet<>();
        orderIds.add(order.getId());
        given(orderRepository.getByIds(orderIds)).willReturn(orders);

        //Act
        GetOrdersResponse getOrdersResponse = orderService.getByIds(orderIds);

        //Assert
        assertEquals(getOrdersResponse.getOrders().size(),1);
    }
}