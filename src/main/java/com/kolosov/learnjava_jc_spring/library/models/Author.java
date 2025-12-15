package com.kolosov.learnjava_jc_spring.library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "library_author")
public class Author extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 1, max = 64)
    private String name;

    @Column(name = "surname", nullable = false)
    @NotBlank
    @Size(min = 1, max = 64)
    private String surname;

    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties("author")
    private List<Book> books;
}
