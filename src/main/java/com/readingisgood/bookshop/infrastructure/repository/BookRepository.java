package com.readingisgood.bookshop.infrastructure.repository;

import com.readingisgood.bookshop.domain.book.Book;

import java.util.Optional;

public interface BookRepository {

    void save(Book book);

    void update(Book book);

    Optional<Book> getById (String bookId);

}
