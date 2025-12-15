package com.kolosov.learnjava_jc_spring.objectMapper.models;

import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "object_mapper_customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerOm extends BaseEntity<Long> {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false)
    @NotBlank
    @Email
    private String email;

    @Column(name = "contact_number", nullable = false)
    @NotBlank
    @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$")
    private String contactNumber;

    @OneToMany(mappedBy = "customer")
    private List<OrderOm> orders;
}
