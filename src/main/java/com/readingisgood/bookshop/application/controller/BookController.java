package com.readingisgood.bookshop.application.controller;

import com.readingisgood.bookshop.application.service.book.BookService;
import com.readingisgood.bookshop.application.service.book.request.AddBookRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity add(@Valid @RequestBody AddBookRequest createBookRequest) {
        String bookId = bookService.add(createBookRequest);
        return ResponseEntity.created(URI.create("/book/" + bookId)).build();
    }


    @PatchMapping("/{bookId}")
    public ResponseEntity update(@PathVariable("bookId") String bookId) {
        bookService.update(bookId);
        return new ResponseEntity(OK);
    }
}
