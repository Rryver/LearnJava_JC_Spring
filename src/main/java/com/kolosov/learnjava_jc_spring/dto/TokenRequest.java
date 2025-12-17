package com.kolosov.learnjava_jc_spring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenRequest {
    private String username;
    private String password;
}
