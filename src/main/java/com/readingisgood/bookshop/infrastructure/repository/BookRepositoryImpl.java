package com.readingisgood.bookshop.infrastructure.repository;

import com.readingisgood.bookshop.domain.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class BookRepositoryImpl implements BookRepository {

    MongoTemplate mongoTemplate;

    @Autowired
    public BookRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save (Book book) {
        mongoTemplate.save(book, "book");
    }

    @Override
    public void update (Book book) {
        mongoTemplate.upsert(
                Query.query(Criteria.where("_id").is(book.getId())),
                new Update().set("currentUsageStock", book.getCurrentUsageStock()), String.class, "book");

    }

    public Optional<Book> getById (String bookId) {
        Book book = mongoTemplate.findById(bookId, Book.class, "book");
        return Optional.ofNullable(book);
    }
}
