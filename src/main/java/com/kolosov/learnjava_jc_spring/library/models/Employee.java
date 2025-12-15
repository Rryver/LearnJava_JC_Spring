package com.kolosov.learnjava_jc_spring.library.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employee")
public class Employee extends BaseEntity<Long> {

    @Column(name = "first_name", nullable = false)
    @NotBlank
    @Size(min = 1, max = 64)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Size(min = 1, max = 64)
    private String lastName;

    @Column(name = "position")
    @NotBlank
    @Size(min = 1, max = 256)
    private String position;

    @Column(name = "salary")
    @Min(0)
    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties("employees")
    private Department department;
}
