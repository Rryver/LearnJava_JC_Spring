package com.kolosov.learnjava_jc_spring.library.services;

import com.kolosov.learnjava_jc_spring.common.CrudService;
import com.kolosov.learnjava_jc_spring.library.models.Book;
import com.kolosov.learnjava_jc_spring.library.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends CrudService<Book, Long> {
    public BookService(BookRepository bookRepository) {
        super(bookRepository);
    }
}
