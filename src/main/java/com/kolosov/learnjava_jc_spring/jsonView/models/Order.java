package com.kolosov.learnjava_jc_spring.jsonView.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import com.kolosov.learnjava_jc_spring.common.views.View;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "json_view_order")
@NoArgsConstructor
@Getter
@Setter
@JsonView(View.BasicView.class)
public class Order extends BaseEntity<Long> {

    @Column(name = "user_id", insertable = false, updatable = false, nullable = false)
    private Long userId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("orders")
    @NotNull
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("order")
    private List<OrderProduct> products;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "total_amount", columnDefinition = "NUMERIC")
    @Schema(hidden = true)
    private Double totalAmount = 0.0;

    public Order(Long id, User user, OrderStatus status) {
        super(id);
        this.user = user;
        this.status = status;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
        this.totalAmount = this.products.stream()
                .map((product) -> product.getPrice() * product.getCount())
                .reduce(Double::sum)
                .orElse(null);
    }
}
