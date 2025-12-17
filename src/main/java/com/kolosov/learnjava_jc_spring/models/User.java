package com.kolosov.learnjava_jc_spring.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> {

    @Column(name = "email")
    @NotBlank
    private String email;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "role")
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_account_non_locked")
    private Boolean isAccountNonLocked;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private FailedLoginAttempt failedLoginAttempt;

    public boolean hasRole(Role role) {
        return this.role.equals(role);
    }
}
