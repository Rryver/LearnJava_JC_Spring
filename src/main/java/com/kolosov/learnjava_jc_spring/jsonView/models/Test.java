package com.kolosov.learnjava_jc_spring.jsonView.models;

import com.kolosov.learnjava_jc_spring.common.views.View;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Test {
    @Null(groups = { View.CreateEntity.class })
    private String str;

    private Integer num;
}
