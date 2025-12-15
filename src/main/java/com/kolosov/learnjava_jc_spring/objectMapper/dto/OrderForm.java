package com.kolosov.learnjava_jc_spring.objectMapper.dto;

import com.kolosov.learnjava_jc_spring.common.views.View;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public class OrderForm {
    @Null(groups = {View.CreateEntity.class})
    @NotNull(groups = {View.UpdateEntity.class})
    Long id;

    @NotNull
    Long customerId;


}
