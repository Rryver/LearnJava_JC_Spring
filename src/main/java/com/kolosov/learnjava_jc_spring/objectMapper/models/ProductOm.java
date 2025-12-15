package com.kolosov.learnjava_jc_spring.objectMapper.models;

import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "object_mapper_product")
@Getter
@Setter
@NoArgsConstructor
public class ProductOm extends BaseEntity<Long> {

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false, columnDefinition = "NUMERIC")
    @NotNull
    @DecimalMin("0.0")
    private Double price;

    @Column(name = "quantity_stock", nullable = false)
    @NotNull
    @Min(0)
    private Integer quantityStock;
}
