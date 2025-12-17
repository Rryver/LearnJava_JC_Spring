package com.kolosov.learnjava_jc_spring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "failed_login_attempts")
@Getter
@Setter
@NoArgsConstructor
public class FailedLoginAttempt extends BaseEntity<Long> {

    @OneToOne
    @JoinColumn(name="user_id", unique=true, nullable=false, updatable=false)
    @JsonIgnoreProperties("failedLoginAttempt")
    private User user;

    @Column(name = "attempts")
    @Min(0)
    private Integer attempts;
}
