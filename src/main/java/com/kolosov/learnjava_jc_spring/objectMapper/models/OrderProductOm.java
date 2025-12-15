package com.kolosov.learnjava_jc_spring.objectMapper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import com.kolosov.learnjava_jc_spring.common.views.View;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "object_mapper_order_product")
@Getter
@Setter
@NoArgsConstructor
@JsonView(View.BasicView.class)
public class OrderProductOm extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "order_id", updatable = false, nullable = false)
    @JsonIgnoreProperties("orderProducts")
    private OrderOm order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("orderProducts")
    private ProductOm product;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price", columnDefinition = "NUMERIC")
    private Double price;

    @Column(name = "count")
    private Integer count;

    public Double calculateTotalPrice() {
        return price * count;
    }
}
