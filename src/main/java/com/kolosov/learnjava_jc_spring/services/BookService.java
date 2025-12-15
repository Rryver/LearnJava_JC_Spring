package com.kolosov.learnjava_jc_spring.services;

import com.kolosov.learnjava_jc_spring.models.Book;
import com.kolosov.learnjava_jc_spring.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.JDBCType;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService implements BookRepository {

    private final JdbcTemplate template;

    @Override
    public List<Book> getAll() {
        return template.query(
                "SELECT * FROM book",
                BeanPropertyRowMapper.newInstance(Book.class)
        );
    }

    @Override
    public Book getById(Long id) {
        Object[] params = {id};
        int[] types = {JDBCType.INTEGER.getVendorTypeNumber()};
        return template.queryForObject(
                "SELECT * FROM book where book.id = ?",
                params,
                types,
                BeanPropertyRowMapper.newInstance(Book.class)
        );
    }

    @Override
    @Transactional
    public void create(Book book) {
        template.update(
                "INSERT INTO book (title, author, publication_year) values (?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear()
        );
    }

    @Transactional
    public void update(Book book) {
        template.update("UPDATE book SET title = ?, author = ?, publication_year = ? WHERE id = ?",
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear(),
                book.getId()
        );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        template.update("DELETE FROM book where id = ?", id);
    }
}
