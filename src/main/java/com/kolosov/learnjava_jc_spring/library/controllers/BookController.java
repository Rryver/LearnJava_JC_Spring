package com.kolosov.learnjava_jc_spring.library.controllers;

import com.kolosov.learnjava_jc_spring.common.AbstractRestController;
import com.kolosov.learnjava_jc_spring.common.views.View;
import com.kolosov.learnjava_jc_spring.library.models.Book;
import com.kolosov.learnjava_jc_spring.library.services.BookService;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BookController.REST_URL)
@RequiredArgsConstructor
public class BookController extends AbstractRestController {
    public static final String REST_URL = AbstractRestController.BASE_REST_URL + "/books";

    private final BookService bookService;

    @GetMapping
    public List<Book> list(Pageable pageable) {
        return bookService.getAll(pageable);
    }

    @GetMapping("/{bookId}")
    public Book getById(@PathVariable(name = "bookId") Long bookId) {
        return bookService.getById(bookId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @Validated({Default.class, View.CreateEntity.class}) Book book) {
        bookService.save(book);
    }

    @PutMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable(name = "bookId") Long bookId,
                       @RequestBody @Validated({Default.class, View.UpdateEntity.class}) Book book
    ) {
        bookService.update(bookId, book);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(name = "bookId") Long bookId) {
        bookService.deleteById(bookId);
    }
}
