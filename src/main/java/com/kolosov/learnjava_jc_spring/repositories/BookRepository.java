package com.kolosov.learnjava_jc_spring.repositories;

import com.kolosov.learnjava_jc_spring.models.Book;

import java.util.List;

public interface BookRepository {

    List<Book> getAll();

    Book getById(Long id);

    void create(Book book);

    void deleteById(Long id);
}
