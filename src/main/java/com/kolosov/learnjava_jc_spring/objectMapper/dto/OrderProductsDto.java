package com.kolosov.learnjava_jc_spring.objectMapper.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderProductsDto {

    Long id;

    @NotNull
    Long productId;

    @NotNull
    Integer count;
}
