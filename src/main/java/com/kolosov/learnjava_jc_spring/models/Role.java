package com.kolosov.learnjava_jc_spring.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    MODERATOR,
    SUPER_ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}