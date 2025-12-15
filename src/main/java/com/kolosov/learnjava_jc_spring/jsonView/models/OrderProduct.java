package com.kolosov.learnjava_jc_spring.jsonView.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import com.kolosov.learnjava_jc_spring.common.views.View;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "json_view_order_product")
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
    @NotBlank
    private String productName;

    @Column(name = "price", columnDefinition = "NUMERIC")
    @DecimalMin("0.0")
    private Double price;

    @Column(name = "count")
    @Min(1)
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
