package com.readingisgood.bookshop.application.service.book;

import com.readingisgood.bookshop.application.service.book.request.AddBookRequest;
import com.readingisgood.bookshop.domain.book.BookSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BookAssemblerTests {

    BookAssembler bookAssembler;

    @BeforeEach
    public void init() {
        bookAssembler = new BookAssembler();
    }

    @Test
    public void addBookRequestToBookSetup() {

        //Arrange
        AddBookRequest request = createBookCommand();


        //Act
        BookSetup result = bookAssembler.createBookRequestToBookSetup(request);

        //Assert
        assertThat(result).usingRecursiveComparison()
                .ignoringFields("id","currentUsageStock")
                .isEqualTo(request);
    }

    private static AddBookRequest createBookCommand() {
        AddBookRequest request = new AddBookRequest();
        request.setName("BookName");
        request.setPrice(15.9);
        request.setStockLimit(new Random().nextInt());
        return request;
    }
}