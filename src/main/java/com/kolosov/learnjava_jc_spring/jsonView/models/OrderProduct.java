package com.kolosov.learnjava_jc_spring.jsonView.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import com.kolosov.learnjava_jc_spring.common.views.View;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_product")
@Getter
@Setter
@NoArgsConstructor
@JsonView(View.BasicView.class)
public class OrderProduct extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("orderProducts")
    private Order order;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price", columnDefinition = "NUMERIC")
    private Double price;

    @Column(name = "count")
    private Integer count;

    public OrderProduct(Long id, String productName, Double price, Integer count) {
        super(id);
        this.productName = productName;
        this.price = price;
        this.count = count;
    }

    public OrderProduct(Long id, Order order, String productName, Double price, Integer count) {
        super(id);
        this.order = order;
        this.productName = productName;
        this.price = price;
        this.count = count;
    }

    public Double calculateTotalPrice() {
        return price * count;
    }
}
