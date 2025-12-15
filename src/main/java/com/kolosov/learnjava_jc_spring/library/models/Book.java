package com.kolosov.learnjava_jc_spring.library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "library_book")
public class Book extends BaseEntity<Long> {

    @Column(name = "title", nullable = false)
    @NotBlank
    @Size(min = 1, max = 64)
    private String title;

    @Column(name = "pages_count")
    @Min(0)
    private Integer pagesCount;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties("books")
    @Valid
    @NotNull
    private Author author;
}
