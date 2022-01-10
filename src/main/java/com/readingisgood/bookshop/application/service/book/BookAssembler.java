package com.readingisgood.bookshop.application.service.book;

import com.readingisgood.bookshop.application.service.book.request.AddBookRequest;
import com.readingisgood.bookshop.domain.book.BookSetup;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookAssembler {

    public static final int CURRENT_USAGE_STOCK_INIT_VALUE = 0;


    public BookSetup createBookRequestToBookSetup (AddBookRequest request) {
        BookSetup bookSetup = new BookSetup();
        bookSetup.setId((request.getId() == null) ? UUID.randomUUID().toString() : request.getId());
        bookSetup.setName(request.getName());
        bookSetup.setPrice(request.getPrice());
        bookSetup.setStockLimit(request.getStockLimit());
        bookSetup.setCurrentUsageStock(CURRENT_USAGE_STOCK_INIT_VALUE);
        return bookSetup;
    }
}
