package com.readingisgood.bookshop.application.service.book;

import com.readingisgood.bookshop.application.exception.NotFoundException;
import com.readingisgood.bookshop.application.service.book.request.AddBookRequest;
import com.readingisgood.bookshop.domain.book.Book;
import com.readingisgood.bookshop.domain.book.BookSetup;
import com.readingisgood.bookshop.domain.exception.BusinessException;
import com.readingisgood.bookshop.infrastructure.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.readingisgood.bookshop.application.MockObjectBuilder.addBookSetup;
import static com.readingisgood.bookshop.application.MockObjectBuilder.addBookCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookAssembler bookAssembler;

    @Test
    void add_WhenCalled_ShouldReturnBook() {
        //Arrange
        AddBookRequest request = addBookCommand();
        BookSetup bookSetup = addBookSetup(request);
        given(bookAssembler.createBookRequestToBookSetup(request)).willReturn(bookSetup);

        //Act
        String result = bookService.add(request);

        //Assert
        assertEquals(result, bookSetup.getId());
    }

    @Test
    void add_WhenBookAlreadyAdded_ShouldThrowBookAlreadyAddedError() {
        //Arrange
        AddBookRequest request = addBookCommand();
        BookSetup bookSetup = addBookSetup(request);
        Book book = new Book(bookSetup);
        given(bookAssembler.createBookRequestToBookSetup(request)).willReturn(bookSetup);
        given(bookRepository.getById(bookSetup.getId())).willReturn(Optional.of(book));

        //Act
        Throwable exception = catchThrowable(() -> bookService.add(request));

        //Assert
        assertThat(exception).isInstanceOf(BusinessException.class);
        assertEquals("BookAlreadyAddedError", ((BusinessException) exception).getKey());
    }

    @Test
    void update_WhenUsageStockLimitLessThanStockLimit_ShouldReturnBook() {
        //Arrange
        AddBookRequest request = addBookCommand();
        BookSetup bookSetup = addBookSetup(request);
        Book book = new Book(bookSetup);
        given(bookRepository.getById(bookSetup.getId())).willReturn(Optional.of(book));
        doAnswer(i -> null).when(bookRepository).update(book);

        //Act
        bookService.update(bookSetup.getId());

        //Assert
        assertEquals(book.getCurrentUsageStock(), 1);
    }

    @Test
    void update_WhenCalled_ShouldReturnNotFoundError() {
        //Arrange
        String bookId = "1223";
        given(bookRepository.getById(bookId)).willReturn(Optional.empty());

        //Act
        Throwable exception = catchThrowable(() -> bookService.update(bookId));

        //Assert
        assertThat(exception).isInstanceOf(NotFoundException.class);
        assertEquals("BookIdNotFoundError", ((NotFoundException) exception).getKey());
    }

    @Test
    void update_WhenStockLimitReached_ShouldReturnStockLimitReachedError() {
        //Arrange
        AddBookRequest request = addBookCommand();
        BookSetup bookSetup = addBookSetup(request);
        bookSetup.setCurrentUsageStock(bookSetup.getStockLimit());
        Book book = new Book(bookSetup);
        given(bookRepository.getById(bookSetup.getId())).willReturn(Optional.of(book));

        //Act
        Throwable exception = catchThrowable(() -> bookService.update(bookSetup.getId()));

        //Assert
        assertThat(exception).isInstanceOf(BusinessException.class);
        assertEquals("StockLimitReachedError", ((BusinessException) exception).getKey());
    }
}
