package com.kolosov.learnjava_jc_spring.library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "department")
public class Department extends BaseEntity<Long> {

    @Column(name = "name")
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties("department")
    private List<Employee> employees;
}
