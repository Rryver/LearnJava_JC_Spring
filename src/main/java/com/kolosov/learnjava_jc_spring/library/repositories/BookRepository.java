package com.kolosov.learnjava_jc_spring.library.repositories;

import com.kolosov.learnjava_jc_spring.library.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;

public interface BookRepository extends JdbcTemplate {
}
