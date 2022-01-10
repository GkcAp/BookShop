package com.readingisgood.bookshop.application.service.book;

import com.readingisgood.bookshop.application.exception.NotFoundException;
import com.readingisgood.bookshop.application.service.book.request.AddBookRequest;
import com.readingisgood.bookshop.domain.book.Book;
import com.readingisgood.bookshop.domain.book.BookSetup;
import com.readingisgood.bookshop.domain.exception.BusinessException;
import com.readingisgood.bookshop.infrastructure.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookAssembler bookAssembler;

    public String add(AddBookRequest createBookRequest ) {
        BookSetup bookSetup = bookAssembler.createBookRequestToBookSetup(createBookRequest);
        Book book = new Book(bookSetup);
        if (bookRepository.getById(book.getId()).isPresent())
            throw new BusinessException("BookAlreadyAddedError");
        else bookRepository.save(book);

        return book.getId();
    }

    public void update(String bookId) {
        Book book = bookRepository.getById(bookId).orElseThrow(() -> new NotFoundException("BookIdNotFoundError"));
        if (book.stockLimitReached()) {
            throw new BusinessException("StockLimitReachedError");
        } else {
            book.increaseCurrentUsageStock();
            bookRepository.update(book);
        }
    }
}
