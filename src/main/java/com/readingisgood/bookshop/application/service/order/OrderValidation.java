package com.readingisgood.bookshop.application.service.order;

import com.readingisgood.bookshop.application.exception.NotFoundException;
import com.readingisgood.bookshop.application.service.order.request.AddOrderRequest;
import com.readingisgood.bookshop.domain.book.Book;
import com.readingisgood.bookshop.domain.exception.BusinessException;
import com.readingisgood.bookshop.infrastructure.repository.BookRepository;
import com.readingisgood.bookshop.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class OrderValidation {

    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;


    public void validateAddOrder(AddOrderRequest request) {
        if(customerRepository.getById(request.getCustomerId()).isEmpty()){
            throw new ValidationException("CustomerIdNotFoundInOrderError");
        }

        HashSet<String> bookIds = request.getBooks();
        HashSet<Book> books = new HashSet<>();
        bookIds.forEach( bookId -> {
            Book book1 = bookRepository.getById(bookId).orElseThrow(() -> new NotFoundException("BookIdNotFoundError"));
            if (book1.stockLimitReached())
                 books.add(book1);
            else throw new BusinessException("StockLimitReachedError");
        });

    }
}
