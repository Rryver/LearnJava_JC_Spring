package com.kolosov.learnjava_jc_spring.objectMapper.dto;

import jakarta.validation.constraints.NotNull;

public class OrderProductsDto {

    @NotNull
    Long productId;

    @NotNull
    Integer count;
}
