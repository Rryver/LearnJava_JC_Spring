package com.kolosov.learnjava_jc_spring.objectMapper.dto;

import com.kolosov.learnjava_jc_spring.objectMapper.models.OrderStatusOm;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderForm {
//    @Null(groups = {View.CreateEntity.class})
//    @NotNull(groups = {View.UpdateEntity.class})
    private Long id;

    @NotNull
    private Long customerId;

    private OrderStatusOm orderStatus;

    @NotEmpty
    private List<OrderProductsDto> orderProducts;

}
