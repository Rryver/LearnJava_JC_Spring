package com.kolosov.learnjava_jc_spring.models;

import com.kolosov.learnjava_jc_spring.views.View;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @Null(groups = {View.CreateEntity.class})
    @NotNull(groups = {View.UpdateEntity.class})
    private Long id;

    @NotBlank
    @Size(min = 1, max = 128)
    private String title;

    @NotNull
    private Integer publicationYear;

    @NotBlank
    @Size(min = 1, max = 128)
    private String author;
}
