package com.kolosov.learnjava_jc_spring.models;

import com.kolosov.learnjava_jc_spring.utils.View;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(groups = { View.CreateEntity.class })
    @NotNull(groups = { View.UpdateEntity.class })
    private ID id;

    public BaseEntity() {
    }

    public BaseEntity(ID id) {
        this.id = id;
    }
}
