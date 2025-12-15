package com.kolosov.learnjava_jc_spring.jsonView.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.kolosov.learnjava_jc_spring.common.BaseEntity;
import com.kolosov.learnjava_jc_spring.common.views.View;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "json_view_user")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonView(View.BasicView.class)
public class User extends BaseEntity<Long> {

    @Column(name = "name")
    @NotBlank
    @Size(min = 1, max = 64)
    private String name;

    @Column(name = "email")
    @NotBlank
    @Email
    @Length(min = 1, max = 64)
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonView(View.UserDetails.class)
    @JsonIgnoreProperties("user")
    @Null(groups = { View.CreateEntity.class, View.UpdateEntity.class })
    @Schema(hidden = true)
    private List<Order> orders;

    public User(Long id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }

    public User(Long aLong, String name, String email, List<Order> orders) {
        super(aLong);
        this.name = name;
        this.email = email;
        this.orders = orders;
    }
}
