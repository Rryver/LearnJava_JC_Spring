package com.kolosov.learnjava_jc_spring.objectMapper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "object_mapper_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderOm extends BaseEntity<Long> {

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("orders")
    private CustomerOm customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("order")
    private List<OrderProductOm> orderProducts;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime orderDate;

    @Column(name = "total_price", columnDefinition = "NUMERIC")
    @Min(0)
    private Double totalPrice;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderStatusOm orderStatus;

    public void setOrderProducts(List<OrderProductOm> orderProducts) {
        this.orderProducts = orderProducts;
        this.totalPrice = this.orderProducts.stream()
                .map((product) -> product.getPrice() * product.getCount())
                .reduce(Double::sum)
                .orElse(null);
    }
}
