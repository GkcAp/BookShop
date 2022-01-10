package com.readingisgood.bookshop.application.controller;

import com.readingisgood.bookshop.application.MockObjectBuilder;
import com.readingisgood.bookshop.application.service.book.BookAssembler;
import com.readingisgood.bookshop.application.service.book.request.AddBookRequest;
import com.readingisgood.bookshop.domain.book.Book;
import com.readingisgood.bookshop.domain.book.BookSetup;
import com.readingisgood.bookshop.infrastructure.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import lombok.SneakyThrows;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTests extends BaseControllerCT {

    @MockBean
    private BookRepository bookRepository;

   // @MockBean
    //private BookAssembler bookAssembler;

    private static final String BOOK_BASE_URL = "/book";

    /*
    @SneakyThrows
    @Test
    void add_WhenNewBatchCame_ShouldAddBatch() {

        //Arrange
        AddBookRequest addBookRequest = MockObjectBuilder.createBookCommand();

        BookSetup bookSetup = MockObjectBuilder.createBookSetupForRequest(addBookRequest);
        Book book = new Book(bookSetup);

        //when(bookAssembler.createBookRequestToBookSetup(addBookRequest)).thenReturn(bookSetup);
        doAnswer(i -> null).when(bookRepository).save(book);

        //Act
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(BOOK_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(httpHeaders)
                .content(objectMapper.writeValueAsString(addBookRequest));

        ResultActions perform = mockMvc.perform(requestBuilder);


        //Assert
        perform.andExpect(status().isAccepted());
        //perform.andExpect(status().is(HttpStatus.ACCEPTED.value()));
        String headerLocationValue = perform.andReturn().getResponse().getHeader("location");
        assertNotNull(headerLocationValue);
        assertTrue(Objects.requireNonNull(headerLocationValue).startsWith(BOOK_BASE_URL + "/"));
    }*/
}
