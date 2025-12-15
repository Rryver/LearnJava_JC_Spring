package com.kolosov.learnjava_jc_spring.controllers;

import com.kolosov.learnjava_jc_spring.views.View;
import com.kolosov.learnjava_jc_spring.models.Book;
import com.kolosov.learnjava_jc_spring.services.BookService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BookController.REST_URL)
@RequiredArgsConstructor
public class BookController {
    public static final String REST_URL = "/books";

    private final BookService bookService;

    @GetMapping
    public List<Book> list() {
        return bookService.getAll();
    }

    @GetMapping("/{bookId}")
    public Book getById(@PathVariable(name = "bookId") Long bookId) {
        return bookService.getById(bookId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @Validated({Default.class, View.CreateEntity.class}) Book book) {
        bookService.create(book);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Validated({Default.class, View.UpdateEntity.class}) Book book) {
        bookService.update(book);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "bookId") Long bookId) {
        bookService.deleteById(bookId);
    }
}
